package com.integration.mercans.jobs.domain;

import static com.integration.mercans.jobs.domain.Constants.ENTITY_WORKER;

import com.integration.mercans.configurations.domain.DynamicConfigurationField;
import com.integration.mercans.jobs.enums.GenderType;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.simpleflatmapper.map.annotation.Column;

@Getter
@Setter
@ToString
public class Worker extends JobRules implements Serializable {

  @Column("name")
  private String name;

  @Column("personalCode")
  private String personalCode;

  @Column("gender")
  private GenderType genderType;

  @Column("numberOfKids")
  private Integer numberOfKids;

  @Column("motherMaidenName")
  private String motherMaidenName;

  @Column("grandmotherMaidenName")
  private String grandmotherMaidenName;

  public Map<String, Object> toMap() {
    Map<String, Object> item = new HashMap<>();
    for (Field declaredField : this.getClass().getDeclaredFields()) {
      try {
        item.put("worker_" + declaredField.getName(), declaredField.get(this));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
    return item;
  }

  public List<String> validate(Map<String, DynamicConfigurationField> configuration) {
    List<String> validationStatus = new ArrayList<>();
    if (configuration.size() > 0) {
      for (Field declaredField : this.getClass().getDeclaredFields()) {
        try {
          String fieldName = declaredField.getName();
          boolean isFieldExist = configuration.containsKey(ENTITY_WORKER + "_" + fieldName);
          Class<?> fieldType = declaredField.getType();
          Object value = declaredField.get(this);
          DynamicConfigurationField dynamicConfigurationField = configuration.get(
              ENTITY_WORKER + "_" + fieldName);
          if (isFieldExist && !validField(fieldType, value, dynamicConfigurationField)) {
            validationStatus.add(ENTITY_WORKER + "_" + fieldName);
          }
        } catch (IllegalAccessException e) {
          System.out.println(String.format("Worker: %s %s", "validate", e.getMessage()));
        }
      }
    }
    return validationStatus;
  }
}

