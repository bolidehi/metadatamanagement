package eu.dzhw.fdz.metadatamanagement.domain.validation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import eu.dzhw.fdz.metadatamanagement.domain.AbstractRdcDomainObjectWithProjectSurvey;
import eu.dzhw.fdz.metadatamanagement.domain.Survey;
import eu.dzhw.fdz.metadatamanagement.repository.SurveyRepository;

/**
 * This validation is for the dependency between variable, survey and fdz project. It should be
 * validate, if the a optional survey is part of the same fdz project.
 * 
 * @author Daniel Katzberg
 *
 */
public class SurveyHasSameDataAcquisitionProjectValidator implements
    ConstraintValidator<SurveyHasSameDataAcquisitionProject, 
        AbstractRdcDomainObjectWithProjectSurvey> {

  @Inject
  private SurveyRepository surveyRepository;

  /*
   * (non-Javadoc)
   * 
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public void initialize(SurveyHasSameDataAcquisitionProject constraintAnnotation) {}

  /*
   * (non-Javadoc)
   * 
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   * javax.validation.ConstraintValidatorContext)
   */
  @Override
  public boolean isValid(AbstractRdcDomainObjectWithProjectSurvey rdcObject,
      ConstraintValidatorContext context) {

    // Optional field. No entry, no issue -> return true.
    if (StringUtils.isEmpty(rdcObject.getSurveyId())) {
      return true;
    }

    // survey is set, but no fdz project. this is not allowed -> validation error.
    if (StringUtils.isEmpty(rdcObject.getDataAcquisitionProjectId())) {
      return false;
    }

    // Check for fdz project name
    Survey survey = this.surveyRepository.findOne(rdcObject.getSurveyId());
    // Wrong id, means in worst case no survey in the db.
    if (survey == null) {
      return false;
    }

    if (survey.getDataAcquisitionProjectId()
        .equals(rdcObject.getDataAcquisitionProjectId())) {
      return true;
    }

    return false;
  }
}
