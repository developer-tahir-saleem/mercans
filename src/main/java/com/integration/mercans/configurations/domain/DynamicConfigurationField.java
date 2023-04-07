package com.integration.mercans.configurations.domain;

import com.integration.mercans.configurations.enums.DataType;
import com.integration.mercans.configurations.enums.FieldType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DynamicConfigurationField {

  private FieldType fieldType;
  private Boolean isMandatory;
  private String sourceField;
  private String targetEntity;
  private String targetField;
  private DataType dataType;
  private String mappingKey;
  private String dateFormat;
  private String validationPattern;
  private Integer regexCaptureGroupNr;

}
