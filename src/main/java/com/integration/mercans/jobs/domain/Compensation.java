package com.integration.mercans.jobs.domain;

import static com.integration.mercans.jobs.domain.Constants.ENTITY_COMPENSATION;

import com.integration.mercans.configurations.domain.DynamicConfigurationField;
import com.integration.mercans.jobs.enums.CurrencyType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.simpleflatmapper.map.annotation.Column;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class Compensation extends JobRules {

  @Column("amount")
  private Integer amount;

  @Column("type")
  private String type;

  @Column("currency")
  private CurrencyType currencyType;

  @DateTimeFormat(pattern = "ddMMyy")
  @Column("effectiveFrom")
  private String effectiveFrom;

  @DateTimeFormat(pattern = "ddMMyy")
  @Column("effectiveTo")
  private String effectiveTo;

  public Map<String, Object> toMap() {
    Map<String, Object> item = new HashMap<>();
    for (Field declaredField : this.getClass().getDeclaredFields()) {
      try {
        item.put(ENTITY_COMPENSATION + "_" + declaredField.getName(), declaredField.get(this));
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
          boolean isFieldExist = configuration.containsKey(ENTITY_COMPENSATION + "_" + fieldName);
          Class<?> fieldType = declaredField.getType();
          Object value = declaredField.get(this);
          DynamicConfigurationField dynamicConfigurationField = configuration.get(
              ENTITY_COMPENSATION + "_" + fieldName);
          if (isFieldExist && !validField(fieldType, value, dynamicConfigurationField)) {
            validationStatus.add(ENTITY_COMPENSATION + "_" + fieldName);
          }
        } catch (IllegalAccessException e) {
          System.out.println(e.getMessage());
        }
      }
    }
    return validationStatus;
  }
}
