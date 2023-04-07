package com.integration.mercans.jobs.domain;

import static com.integration.mercans.jobs.domain.Constants.ENTITY_CONTRACT;

import com.integration.mercans.configurations.domain.DynamicConfigurationField;
import com.integration.mercans.jobs.enums.ContractType;
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
public class Contract extends JobRules {

  @Column("workerId")
  private String workerId;

  @Column("signatureDate")
  private String signatureDate;

  @Column("workStartDate")
  private String workStartDate;

  @Column("type")
  private ContractType type;

  @Column("workStartDate")
  private String endDate;

  public <T> Map<String, Object> toMap() {
    Map<String, Object> item = new HashMap<>();
    for (Field declaredField : this.getClass().getDeclaredFields()) {
      try {
        item.put("contract_" + declaredField.getName(), declaredField.get(this));
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
          boolean isFieldExist = configuration.containsKey(ENTITY_CONTRACT + "_" + fieldName);
          Class<?> fieldType = declaredField.getType();
          Object value = declaredField.get(this);
          DynamicConfigurationField dynamicConfigurationField = configuration.get(
              ENTITY_CONTRACT + "_" + fieldName);
          if (isFieldExist && !validField(fieldType, value, dynamicConfigurationField)) {
            validationStatus.add(ENTITY_CONTRACT + "_" + fieldName);
          }

        } catch (IllegalAccessException e) {
          System.out.println(e.getMessage());
        }
      }
    }
    return validationStatus;
  }

}
