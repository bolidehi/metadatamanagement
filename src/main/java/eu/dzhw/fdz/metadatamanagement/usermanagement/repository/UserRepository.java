package eu.dzhw.fdz.metadatamanagement.usermanagement.repository;

import eu.dzhw.fdz.metadatamanagement.usermanagement.domain.Authority;
import eu.dzhw.fdz.metadatamanagement.usermanagement.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Spring Data MongoDB repository for the User entity.
 */
@RepositoryRestResource(exported = false)
public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findOneByActivationKey(String activationKey);

  List<User> findAllByActivatedIsFalseAndCreatedDateBefore(LocalDateTime dateTime);

  List<User> findAllByAuthoritiesContaining(Authority authority);

  List<User> findAllByLoginLikeOrEmailLike(String login, String email);

  List<User> findAllByLoginIn(Set<String> userLoginNames);

  Optional<User> findOneByResetKey(String resetKey);

  Optional<User> findOneByEmail(String email);

  Optional<User> findOneByLogin(String login);

  void deleteByEmail(String email);
}
