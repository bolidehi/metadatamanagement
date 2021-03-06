package eu.dzhw.fdz.metadatamanagement.studymanagement.rest;

import java.util.List;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.dzhw.fdz.metadatamanagement.studymanagement.domain.Study;
import eu.dzhw.fdz.metadatamanagement.studymanagement.service.StudyVersionsService;
import lombok.RequiredArgsConstructor;

/**
 * Rest Controller for retrieving previous version of the study domain object.
 * 
 * @author René Reitmann
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudyVersionsResource {

  private final StudyVersionsService studyVersionsService;
    
  /**
   * Get the previous 5 versions of the study.
   * 
   * @param id The id of the study
   * @param limit like page size
   * @param skip for skipping n versions
   * 
   * @return A list of previous study versions
   */
  @GetMapping("/studies/{id}/versions")
  public ResponseEntity<?> findPreviousStudyVersions(@PathVariable String id,
      @RequestParam(name = "limit", defaultValue = "5") Integer limit,
      @RequestParam(name = "skip", defaultValue = "0") Integer skip) {
    List<Study> studyVersions = studyVersionsService.findPreviousVersions(id, limit, skip);
    
    if (studyVersions == null) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok()
        .cacheControl(CacheControl.noStore())
        .body(studyVersions);
  }
}
