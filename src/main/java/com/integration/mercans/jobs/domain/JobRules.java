package com.integration.mercans.jobs.domain;

import com.integration.mercans.configurations.domain.DynamicConfigurationField;
import com.integration.mercans.configurations.enums.DataType;
import com.integration.mercans.jobs.service.JobsValidation;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;


public class JobRules implements JobsValidation, Serializable {

  @Override
  public String createPersonalCode(String workStartDate) {
    Random random = new Random();
    // Generate a random integer between 0 and 255
    int orderNumber = random.nextInt(256);
    // Convert to 2-digit hexadecimal string
    String formattedOrderNumber = String.format("%02X",
        orderNumber);
    return workStartDate + formattedOrderNumber;
  }

  @Override
  public boolean validField(Class<?> type, Object o, DynamicConfigurationField field) {
    if (type == String.class) {
      String str = (String) o;
      return !isNull(str) && fieldValidString(str, field);
    } else if (type == Integer.class) {
      Integer integer = (Integer) o;
      return integer != null;
    } else if (type == BigDecimal.class) {
      BigDecimal bigDecimal = (BigDecimal) o;
      return bigDecimal != null;
    }
    return false;
  }

  @Override
  public boolean fieldValidString(String str, DynamicConfigurationField field) {
    if (field.getValidationPattern() != null) {
      return fieldMatchPattern(str, field.getValidationPattern());
    } else if (field.getDataType().equals(DataType.Date)) {
      return fieldValidDate(str);
    }
    return true;
  }

  @Override
  public boolean fieldValidDate(String strDate) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy", Locale.getDefault())
        .withResolverStyle(ResolverStyle.SMART);
    if (strDate == null) {
      return false;
    }
    if (strDate.length() <= 6 && strDate.length() > 4) {
      strDate = strDate.length() == 5 ? "0" + strDate : strDate;
    }
    try {
      LocalDate.parse(strDate, dateFormatter);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean fieldMatchPattern(String str, String validationPattern) {
    return Pattern.matches(validationPattern, str);
  }

  @Override
  public boolean isNull(String nullString) {
    return nullString == null || nullString.isEmpty() || nullString.isBlank();
  }

}
