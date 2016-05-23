package eu.dzhw.fdz.metadatamanagement.projectmanagement.rest.dto;

import java.util.List;

import com.google.common.base.MoreObjects;

/**
 * A DTO for the collection of post validation errors.
 * 
 * @author Daniel Katzberg
 *
 */
public class PostValidationErrorsDto {

  private List<String> errors;

  /**
   * Constructor for the PostValidationErrorsDto.
   * 
   * @param errors A list of post validation errors.
   */
  public PostValidationErrorsDto(List<String> errors) {
    this.errors = errors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("errors", errors)
      .toString();
  }

  /* GETTER / SETTER */
  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }
}
