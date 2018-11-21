package eu.dzhw.fdz.metadatamanagement.projectmanagement.service;

import eu.dzhw.fdz.metadatamanagement.mailmanagement.service.MailService;
import eu.dzhw.fdz.metadatamanagement.projectmanagement.domain.DataAcquisitionProject;
import eu.dzhw.fdz.metadatamanagement.projectmanagement.repository.DataAcquisitionProjectRepository;
import eu.dzhw.fdz.metadatamanagement.usermanagement.domain.User;
import eu.dzhw.fdz.metadatamanagement.usermanagement.repository.UserRepository;
import eu.dzhw.fdz.metadatamanagement.usermanagement.security.AuthoritiesConstants;
import eu.dzhw.fdz.metadatamanagement.usermanagement.security.UserInformationProvider;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AfterDeleteEvent;
import org.springframework.data.rest.core.event.AfterSaveEvent;
import org.springframework.data.rest.core.event.BeforeDeleteEvent;
import org.springframework.data.rest.core.event.BeforeSaveEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for the Data Acquisition Project. Handles calls to the mongo db.
 * @author Daniel Katzberg
 */
@Service
@RepositoryEventHandler
public class DataAcquisitionProjectService {

  private DataAcquisitionProjectRepository acquisitionProjectRepository;

  private ApplicationEventPublisher eventPublisher;

  private UserInformationProvider userInformationProvider;

  private DataAcquisitionProjectChangesProvider changesProvider;

  private UserRepository userRepository;

  private MailService mailService;

  /**
   * Creates a new {@link DataAcquisitionProjectService} instance.
   */
  public DataAcquisitionProjectService(DataAcquisitionProjectRepository dataAcquisitionProjectRepo,
                                       ApplicationEventPublisher applicationEventPublisher,
                                       UserInformationProvider userInformationProvider,
                                       DataAcquisitionProjectChangesProvider changesProvider,
                                       UserRepository userRepository,
                                       MailService mailService) {
    this.acquisitionProjectRepository = dataAcquisitionProjectRepo;
    this.eventPublisher = applicationEventPublisher;
    this.userInformationProvider = userInformationProvider;
    this.changesProvider = changesProvider;
    this.userRepository = userRepository;
    this.mailService = mailService;
  }

  /**
   * Saves a Data Acquisition Project.
   */
  public void saveDataAcquisitionProject(DataAcquisitionProject dataAcquisitionProject) {
    this.eventPublisher.publishEvent(new BeforeSaveEvent(dataAcquisitionProject));
    this.acquisitionProjectRepository.save(dataAcquisitionProject);
    this.eventPublisher.publishEvent(new AfterSaveEvent(dataAcquisitionProject));
  }

  /**
   * Deletes a Data Acquisition Project, it it hasn't been released before.
   * @param dataAcquisitionProject A representation of the Data Acquisition Project.
   * @return True = The Project is deleted. False = The Project is not deleted.
   */
  public boolean deleteDataAcquisitionProject(DataAcquisitionProject dataAcquisitionProject) {

    //just delete project, if it has not been released before.
    if (!dataAcquisitionProject.getHasBeenReleasedBefore()) {
      this.eventPublisher.publishEvent(new BeforeDeleteEvent(dataAcquisitionProject));
      this.acquisitionProjectRepository.delete(dataAcquisitionProject);
      this.eventPublisher.publishEvent(new AfterDeleteEvent(dataAcquisitionProject));
    }

    return !dataAcquisitionProject.getHasBeenReleasedBefore();
  }

  /**
   * Searches for {@link DataAcquisitionProject} items for the given id. The result
   * may be limited if the current user is not an admin or publisher.
   * @param projectId Project id
   * @return A list of {@link DataAcquisitionProject}
   */
  public List<DataAcquisitionProject> findByIdLikeOrderByIdAsc(String projectId) {
    String loginName = userInformationProvider.getUserLogin();

    if (isAdmin() || isPublisher()) {
      return acquisitionProjectRepository.findByIdLikeOrderByIdAsc(projectId);
    } else {
      return acquisitionProjectRepository.findAllByIdLikeAndPublisherIdOrderByIdAsc(
          projectId,
          loginName
      );
    }
  }

  /**
   * Searches for a {@link DataAcquisitionProject} with the given id. The result depends on the
   * user's role. Publishers and administrators find everything (provided the project with the given
   * id exists). In all other cases the user must be a data provider for the requested project.
   * @param projectId Project id
   * @return Optional of {@link DataAcquisitionProject}, might contain {@code null} if the project
   *     doesn't exist or if the user has insufficient access rights.
   */
  public Optional<DataAcquisitionProject> findDataAcquisitionProjectById(String projectId) {
    String loginName = userInformationProvider.getUserLogin();

    if (isAdmin() || isPublisher()) {
      return acquisitionProjectRepository.findById(projectId);
    } else {
      return acquisitionProjectRepository.findByProjectIdAndDataProviderId(
          projectId,
          loginName
      );
    }
  }

  private boolean isAdmin() {
    return userInformationProvider.isUserInRole(AuthoritiesConstants.ADMIN);
  }

  private boolean isPublisher() {
    return userInformationProvider.isUserInRole(AuthoritiesConstants.PUBLISHER);
  }

  @HandleBeforeSave
  void onHandleBeforeSave(DataAcquisitionProject newDataAcquisitionProject) {
    Optional<DataAcquisitionProject> oldProject = acquisitionProjectRepository
        .findById(newDataAcquisitionProject.getId());
    changesProvider.put(oldProject.orElse(null), newDataAcquisitionProject);
  }

  @HandleAfterSave
  void onHandleAfterSave(DataAcquisitionProject newDataAcquisitionProject) {
    final String projectId = newDataAcquisitionProject.getId();

    List<String> addedPublishers = changesProvider.getAddedPublisherUserNamesList(projectId);
    List<String> removedPublishers = changesProvider.getRemovedPublisherUserNamesList(projectId);

    List<String> addedDataProviders = changesProvider.getAddedDataProviderUserNamesList(projectId);
    List<String> removedDataProviders = changesProvider
        .getRemovedDataProviderUserNamesList(projectId);

    UserFetchResult users = fetchUsersForUserNames(addedPublishers, removedPublishers,
        addedDataProviders, removedDataProviders);

    sendMailToPublishers(users.addedPublisherUsers, users.removedPublisherUsers);
    sendMailToDataProviders(users.addedDataProviders, users.removedDataProviders);
  }

  private void sendMailToPublishers(List<User> addedPublishers, List<User> removedPublishers) {
    mailService.sendPublishersAddedMail(addedPublishers);
    mailService.sendPublisherRemovedMail(removedPublishers);
  }

  private void sendMailToDataProviders(List<User> addedDataProviders,
                                       List<User> removedDataProviders) {
    mailService.sendDataProviderAddedMail(addedDataProviders);
    mailService.sendDataProviderRemovedMail(removedDataProviders);
  }

  private UserFetchResult fetchUsersForUserNames(List<String> addedPublishers,
                                                 List<String> removedPublishers,
                                                 List<String> addedDataProviders,
                                                 List<String> removedDataProviders) {
    List<String> userLoginNames = new ArrayList<>(addedPublishers);
    userLoginNames.addAll(removedPublishers);
    userLoginNames.addAll(addedDataProviders);
    userLoginNames.addAll(removedDataProviders);

    List<User> users = userRepository.findAllByLoginIn(userLoginNames);

    List<User> addedPublisherUsers = users.stream()
        .filter(u -> addedPublishers.contains(u.getLogin())).collect(Collectors.toList());
    List<User> removedPublisherUsers = users.stream()
        .filter(u -> removedPublishers.contains(u.getLogin())).collect(Collectors.toList());
    List<User> addedDataProviderUsers = users.stream()
        .filter(u -> addedDataProviders.contains(u.getLogin())).collect(Collectors.toList());
    List<User> removedDataProviderUsers = users.stream()
        .filter(u -> removedDataProviders.contains(u.getLogin())).collect(Collectors.toList());

    return new UserFetchResult(addedPublisherUsers, removedPublisherUsers, addedDataProviderUsers,
        removedDataProviderUsers);
  }

  /**
   * Encapsulates repository query result for added or removed publishers and data providers.
   */
  private static class UserFetchResult {
    private List<User> addedPublisherUsers;
    private List<User> removedPublisherUsers;
    private List<User> addedDataProviders;
    private List<User> removedDataProviders;

    UserFetchResult(List<User> addedPublisherUsers, List<User> removedPublisherUsers,
                           List<User> addedDataProviders, List<User> removedDataProviders) {
      this.addedPublisherUsers = addedPublisherUsers;
      this.removedPublisherUsers = removedPublisherUsers;
      this.addedDataProviders = addedDataProviders;
      this.removedDataProviders = removedDataProviders;
    }
  }
}
