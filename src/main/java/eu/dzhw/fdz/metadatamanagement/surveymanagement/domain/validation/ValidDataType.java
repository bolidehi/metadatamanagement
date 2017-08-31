package eu.dzhw.fdz.metadatamanagement.surveymanagement.domain.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import eu.dzhw.fdz.metadatamanagement.variablemanagement.domain.DataTypes;

/**
 * Annotation for the validator for the data type. 
 * Only values of the {@link DataTypes} Strings are allowed.
 * 
 * @author Daniel Katzberg
 *
 */
@Documented
@Constraint(validatedBy = {ValidDataTypeValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDataType {
  /**
   * Defines the default error message.
   */
  String message() default "{eu.dzhw.fdz.metadatamanagement.domain.validation."
      + "validsurveydatatype.message}";

  /**
   * This contains groups.
   */
  Class<?>[] groups() default {};

  /**
   * This method contains the payload.
   */
  Class<? extends Payload>[] payload() default {};
}
