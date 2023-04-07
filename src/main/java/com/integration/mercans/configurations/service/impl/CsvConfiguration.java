package com.integration.mercans.configurations.service.impl;

import com.integration.mercans.configurations.service.AbstractDynamicConfigurations;
import com.integration.mercans.configurations.service.DynamicConfigurationService;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CsvConfiguration<T> extends AbstractDynamicConfigurations implements
    DynamicConfigurationService<T> {

  @Override
  public List<T> getAll() {
    return Collections.emptyList();
  }

  @Override
  public T get() {
    return null;
  }

}
