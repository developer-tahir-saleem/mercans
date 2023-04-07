package com.integration.mercans.jobs.domain;

import static com.integration.mercans.jobs.domain.Constants.ENTITY_PAY;

import com.integration.mercans.configurations.domain.DynamicConfigurationField;
import com.integration.mercans.jobs.enums.CurrencyType;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.simpleflatmapper.map.annotation.Column;

@Getter
@Setter
@ToString
public class Pay extends JobRules {

  @Column("amount")
  private BigDecimal amount;

  @Column("currency")
  private CurrencyType currencyType;

  @Column("effectiveFrom")
  private String effectiveFrom;

  @Column("effectiveTo")
  private String effectiveTo;


  public List<String> validate(Map<String, DynamicConfigurationField> configuration) {
    List<String> validationStatus = new ArrayList<>();
    if (configuration.size() > 0) {
      for (Field declaredField : this.getClass().getDeclaredFields()) {
        try {
          String fieldName = declaredField.getName();
          boolean isFieldExist = configuration.containsKey(ENTITY_PAY + "_" + fieldName);
          Class<?> fieldType = declaredField.getType();
          Object value = declaredField.get(this);
          DynamicConfigurationField dynamicConfigurationField = configuration.get(
              ENTITY_PAY + "_" + fieldName);
          if (isFieldExist && !validField(fieldType, value, dynamicConfigurationField)) {
            validationStatus.add(ENTITY_PAY + "_" + fieldName);
          }

        } catch (IllegalAccessException e) {
          System.out.println(String.format("Pay: %s %s", "validate", e.getMessage()));
        }
      }
    }
    return validationStatus;
  }
}
