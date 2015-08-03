package eu.dzhw.fdz.metadatamanagement.web.resources;

import org.springframework.hateoas.ResourceSupport;

import eu.dzhw.fdz.metadatamanagement.data.variablemanagement.documents.VariableDocument;

/**
 * Build and Wrap a VariableDocument resource from a VariableDocument object.
 * 
 * @author Amine Limouri
 */
public class VariableResource extends ResourceSupport {

  private VariableDocument variableDocument;

  public VariableResource(VariableDocument variableDocument) {
    this.setVariableDocument(variableDocument);
  }

  public VariableDocument getVariableDocument() {
    return variableDocument;
  }

  public void setVariableDocument(VariableDocument variableDocument) {
    this.variableDocument = variableDocument;
  }

}
