package eu.dzhw.fdz.metadatamanagement.common.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.javers.core.metamodel.annotation.ValueObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representation of the resolution of images.
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ValueObject
public class Resolution implements Serializable {

  private static final long serialVersionUID = -5319528181583261719L;

  /**
   * The width in pixel.
   */
  @NotNull(message = "global.error.resolution.width-x.not-null")
  private Integer widthX;

  /**
   * The height in pixel.
   */
  @NotNull(message = "global.error.resolution.height-y.not-null")
  private Integer heightY;
}
