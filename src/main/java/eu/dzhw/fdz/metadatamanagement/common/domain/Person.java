package eu.dzhw.fdz.metadatamanagement.common.domain;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.javers.core.metamodel.annotation.ValueObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A representation of a person.
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ValueObject
public class Person implements Serializable {

  private static final long serialVersionUID = 1594224585287945999L;

  /**
   * The first name of the person.
   * 
   * Must not be empty.
   */
  @NotEmpty(message = "global.error.person.first-name.not-empty")
  private String firstName;

  /**
   * The middle name of the person.
   */
  private String middleName;

  /**
   * The last name of the person.
   * 
   * Must not be empty.
   */
  @NotEmpty(message = "global.error.person.last-name.not-empty")
  private String lastName;
}
