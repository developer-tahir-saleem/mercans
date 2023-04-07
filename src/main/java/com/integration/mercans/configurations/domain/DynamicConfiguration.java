package com.integration.mercans.configurations.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DynamicConfiguration {

  private String fileNamePattern;
  private Map<String, Map<String, String>> mappings;
  private List<DynamicConfigurationField> fields;

  public Map<String, DynamicConfigurationField> getFieldsMap(String entity) {
    Map<String, DynamicConfigurationField> map = new HashMap<>();
    for (DynamicConfigurationField field : this.getFields()) {
      if (field.getSourceField().contains(entity) && field.getIsMandatory()) {
        map.put(field.getSourceField(), field);
      }
    }
    return map;
  }
}
