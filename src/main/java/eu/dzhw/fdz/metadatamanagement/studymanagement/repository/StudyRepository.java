package eu.dzhw.fdz.metadatamanagement.studymanagement.repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import eu.dzhw.fdz.metadatamanagement.common.domain.I18nString;
import eu.dzhw.fdz.metadatamanagement.common.domain.projections.IdAndVersionProjection;
import eu.dzhw.fdz.metadatamanagement.common.repository.BaseRepository;
import eu.dzhw.fdz.metadatamanagement.studymanagement.domain.Study;
import eu.dzhw.fdz.metadatamanagement.studymanagement.domain.projection.StudySubDocumentProjection;

/**
 * Spring Data MongoDB repository for the data acquisitionProject entity.
 *
 * @author Daniel Katzberg
 */
@RepositoryRestResource(path = "studies", excerptProjection = IdAndVersionProjection.class)
@JaversSpringDataAuditable
public interface StudyRepository extends BaseRepository<Study, String>, StudyRepositoryCustom {

  @RestResource(exported = false)
  IdAndVersionProjection findOneIdAndVersionById(String id);

  @RestResource(exported = false)
  Study findOneByDataAcquisitionProjectId(String dataAcquisitionProjectId);

  @RestResource(exported = true)
  List<Study> findByDataAcquisitionProjectId(
      @Param("dataAcquisitionProjectId") String dataAcquisitionProjectId);

  @RestResource(exported = false)
  Stream<Study> streamByDataAcquisitionProjectId(String dataAcquisitionProjectId);

  @RestResource(exported = false)
  Stream<IdAndVersionProjection> streamAllIdAndVersionsBy();

  @RestResource(exported = false)
  Stream<IdAndVersionProjection> streamIdsByIdIn(Collection<String> studyIds);

  @RestResource(exported = false)
  List<StudySubDocumentProjection> findSubDocumentsByIdIn(Collection<String> studyIds);

  @RestResource(exported = false)
  StudySubDocumentProjection findOneSubDocumentById(String studyId);

  @RestResource(exported = false)
  Stream<IdAndVersionProjection> streamIdsByDataAcquisitionProjectId(String id);

  @RestResource(exported = false)
  boolean existsByStudySeries(I18nString studySeries);

  @RestResource(exported = false)
  Stream<Study> streamByDataAcquisitionProjectIdAndShadowIsFalse(String dataAcquisitionProjectId);

  @RestResource(exported = false)
  Stream<Study> streamByDataAcquisitionProjectIdAndSuccessorIdIsNullAndShadowIsTrue(
      String oldProjectId);

  @RestResource(exported = false)
  Stream<Study> streamByDataAcquisitionProjectIdAndShadowIsTrue(String oldProjectId);

  @RestResource(exported = false)
  Stream<IdAndVersionProjection> streamIdsByMasterIdInAndShadowIsTrueAndSuccessorIdIsNull(
      Collection<String> dataSetIds);

  @RestResource(exported = false)
  Stream<IdAndVersionProjection> streamIdsByMasterIdIn(Collection<String> dataSetIds);

  @RestResource(exported = false)
  Stream<Study> findByDataAcquisitionProjectIdAndShadowIsTrueAndSuccessorIdIsNull(
      String dataAcquisitionProjectId);
}
