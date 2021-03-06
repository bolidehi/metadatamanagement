package eu.dzhw.fdz.metadatamanagement.common.domain;

/**
 * Exception that should be thrown if a client tries to update a shadow version of a domain object.
 */
public class ShadowCopySaveNotAllowedException extends IllegalArgumentException {
  private static final long serialVersionUID = 952951203132962140L;
}
