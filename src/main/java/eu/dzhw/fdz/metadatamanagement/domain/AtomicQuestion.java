package eu.dzhw.fdz.metadatamanagement.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.base.MoreObjects;

import eu.dzhw.fdz.metadatamanagement.domain.validation.DataAcquisitionProjectExists;
import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * Atomic Question.
 * 
 * @author Daniel Katzberg
 *
 */
@Document(collection = "atomic_questions")
@GeneratePojoBuilder(intoPackage = "eu.dzhw.fdz.metadatamanagement.domain.builders")
@CompoundIndex(def = "{name: 1, questionnaireId: 1}", unique = true)
public class AtomicQuestion extends AbstractRdcDomainObject {

  @Id
  @NotEmpty(message = "{error.atomicQuestion.id.isEmpty}")
  private String id;

  private I18nString type;

  private String name;

  private String compositeQuestionName;

  private I18nString footnote;

  private I18nString questionText;

  private I18nString instruction;

  private I18nString introduction;

  private I18nString sectionHeader;

  @NotEmpty(message = "{error.dataAcquisitionProject.id.isEmpty}")
  @DataAcquisitionProjectExists
  private String dataAcquisitionProjectId;

  @NotEmpty(message = "{error.questionnaire.id.isEmpty}")
  private String questionnaireId;

  @NotEmpty(message = "{error.variable.id.isEmpty}")
  private String variableId;

  /*
   * (non-Javadoc)
   * 
   * @see eu.dzhw.fdz.metadatamanagement.domain.AbstractRdcDomainObject#getId()
   */
  @Override
  public String getId() {
    return this.id;
  }

  /*
   * (non-Javadoc)
   * 
   * @see eu.dzhw.fdz.metadatamanagement.domain.AbstractRdcDomainObject#toString()
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("super", super.toString())
      .add("id", id)
      .add("type", type)
      .add("name", name)
      .add("compositeQuestionName", compositeQuestionName)
      .add("footnote", footnote)
      .add("questionText", questionText)
      .add("instruction", instruction)
      .add("introduction", introduction)
      .add("sectionHeader", sectionHeader)
      .add("dataAcquisitionProjectId", dataAcquisitionProjectId)
      .add("questionnaireId", questionnaireId)
      .add("variableId", variableId)
      .toString();
  }

  /* GETTER / SETTER */
  public I18nString getType() {
    return type;
  }

  public void setType(I18nString type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCompositeQuestionName() {
    return compositeQuestionName;
  }

  public void setCompositeQuestionName(String compositeQuestionName) {
    this.compositeQuestionName = compositeQuestionName;
  }

  public I18nString getFootnote() {
    return footnote;
  }

  public void setFootnote(I18nString footnote) {
    this.footnote = footnote;
  }

  public I18nString getQuestionText() {
    return questionText;
  }

  public void setQuestionText(I18nString questionText) {
    this.questionText = questionText;
  }

  public I18nString getInstruction() {
    return instruction;
  }

  public void setInstruction(I18nString instruction) {
    this.instruction = instruction;
  }

  public I18nString getIntroduction() {
    return introduction;
  }

  public void setIntroduction(I18nString introduction) {
    this.introduction = introduction;
  }

  public I18nString getSectionHeader() {
    return sectionHeader;
  }

  public void setSectionHeader(I18nString sectionHeader) {
    this.sectionHeader = sectionHeader;
  }

  public String getDataAcquisitionProjectId() {
    return dataAcquisitionProjectId;
  }

  public void setDataAcquisitionProjectId(String dataAcquisitionProjectId) {
    this.dataAcquisitionProjectId = dataAcquisitionProjectId;
  }

  public String getQuestionnaireId() {
    return questionnaireId;
  }

  public void setQuestionnaireId(String questionnaireId) {
    this.questionnaireId = questionnaireId;
  }

  public String getVariableId() {
    return variableId;
  }

  public void setVariableId(String variableId) {
    this.variableId = variableId;
  }

  public void setId(String id) {
    this.id = id;
  }
}
