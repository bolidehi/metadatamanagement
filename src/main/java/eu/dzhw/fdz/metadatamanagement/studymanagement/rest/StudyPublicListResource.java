package eu.dzhw.fdz.metadatamanagement.studymanagement.rest;

import java.io.IOException;

import javax.validation.constraints.Max;

import org.springframework.data.domain.Page;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.dzhw.fdz.metadatamanagement.searchmanagement.documents.StudySearchDocument;
import eu.dzhw.fdz.metadatamanagement.studymanagement.service.StudyListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Ednpoint to deliver released studies.
 *
 * @author tgehrke
 *
 */
@Slf4j
@Controller
@RequestMapping("/api")
@Validated
@Tag(name = "Study List Resource", description = "Endpoint for retrieving released studies.")
@RequiredArgsConstructor
public class StudyPublicListResource {

  private final StudyListService studylistService;

  /**
   * Request a pageble list of released studies.
   *
   * @param page the page. default 0
   * @param size the size of a page. default 5
   * @return the page object. containing the list of studies as content and metadata regarding the
   *         paging.
   */
  @GetMapping(value = "/studies")
  @Operation(summary = "Get the paged list of currently released studies.")
  public ResponseEntity<Page<StudySearchDocument>> listStudies(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "5") @Max(20) int size) {
    try {
      Page<StudySearchDocument> loadStudies = studylistService.loadStudies(page, size);
      return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(loadStudies);
    } catch (IOException e) {
      log.warn("requesting the list of studies failed", e);
      return ResponseEntity.badRequest().body(null);
    }
  }
}
