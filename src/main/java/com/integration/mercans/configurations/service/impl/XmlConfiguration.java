package com.integration.mercans.configurations.service.impl;

import com.integration.mercans.configurations.service.AbstractDynamicConfigurations;
import com.integration.mercans.configurations.service.DynamicConfigurationService;
import com.integration.mercans.parsers.enums.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class XmlConfiguration<T> extends AbstractDynamicConfigurations implements DynamicConfigurationService<T> {

    @Override
    public List<T> getAll() {
        return Collections.emptyList();
    }

    @Override
    public T get() {
        return null;
    }

}
