package com.integration.mercans.configurations.service;

import com.integration.mercans.parsers.enums.ContentType;

import java.util.List;

public interface DynamicConfigurationService<T> {
    List<T> getAll();
    T get();
}
