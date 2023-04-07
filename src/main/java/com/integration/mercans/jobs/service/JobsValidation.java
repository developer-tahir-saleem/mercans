package com.integration.mercans.jobs.service;


import com.integration.mercans.configurations.domain.DynamicConfigurationField;
import com.integration.mercans.jobs.domain.JobRules;
import java.util.List;
import java.util.Map;

/**
 * This interface defines a set of validation methods for jobs configurations.
 *
 * @version 1.0
 */
public interface JobsValidation {

  String createPersonalCode(String workStartDate);

  /**
   * Validates a single configuration field of a given type against a dynamic configuration field,
   * and returns whether it is valid.
   *
   * @param type  The type of the configuration field.
   * @param o     The configuration field to validate.
   * @param field The dynamic configuration field to validate against.
   * @return true if the configuration field is valid, false otherwise.
   */
  boolean validField(Class<?> type, Object o, DynamicConfigurationField field);

  /**
   * Validates a string configuration field against a dynamic configuration field, and returns
   * whether it is valid.
   *
   * @param str   The string configuration field to validate.
   * @param field The dynamic configuration field to validate against.
   * @return true if the string configuration field is valid, false otherwise.
   */
  boolean fieldValidString(String str, DynamicConfigurationField field);

  /**
   * Validates a string representation of a date against a dynamic configuration field, and returns
   * whether it is valid.
   *
   * @param strDate The string representation of the date to validate.
   * @return true if the string representation of the date is valid, false otherwise.
   */
  boolean fieldValidDate(String strDate);

  /**
   * Validates a string against a validation pattern, and returns whether it matches.
   *
   * @param str               The string to validate.
   * @param validationPattern The validation pattern to match against.
   * @return true if the string matches the validation pattern, false otherwise.
   */
  boolean fieldMatchPattern(String str, String validationPattern);

  /**
   * Checks whether a string is null or empty.
   *
   * @param nullString The string to check.
   * @return true if the string is null or empty, false otherwise.
   */
  boolean isNull(String nullString);

}