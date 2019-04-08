package eu.dzhw.fdz.metadatamanagement.instrumentmanagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.dzhw.fdz.metadatamanagement.instrumentmanagement.domain.Instrument;
import eu.dzhw.fdz.metadatamanagement.instrumentmanagement.service.InstrumentService;
import eu.dzhw.fdz.metadatamanagement.usermanagement.security.AuthoritiesConstants;

/**
 * Rest Controller for deleting instruments of a data acquisition project.
 * 
 * @author tgehrke
 *
 */

@RestController
@RequestMapping("/api")
public class DeleteAllInstrumentsResourceController {
  @Autowired
  private InstrumentService instrumentService;

  /**
   * delete all instruments from data acquisition project.
   * 
   * @param id the Id of the project.
   * @return no Content.
   */
  @Secured(value = {AuthoritiesConstants.DATA_PROVIDER, AuthoritiesConstants.PUBLISHER})
  @DeleteMapping(value = "/data-acquisition-projects/{id}/instruments")
  public ResponseEntity<Instrument> delete(@PathVariable String id) {
    instrumentService.deleteAllInstrumentsByProjectId(id);
    return ResponseEntity.noContent().build();
  }
}
