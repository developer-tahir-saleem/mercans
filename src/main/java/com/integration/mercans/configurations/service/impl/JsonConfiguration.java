package com.integration.mercans.configurations.service.impl;

import com.integration.mercans.configurations.service.AbstractDynamicConfigurations;
import com.integration.mercans.configurations.service.DynamicConfigurationService;
import com.integration.mercans.parsers.enums.ContentType;
import com.integration.mercans.parsers.factory.ParserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JsonConfiguration<T,D> extends AbstractDynamicConfigurations implements DynamicConfigurationService<T> {
    private ParserFactory<T,D> parserFactory;

    @Autowired
    public JsonConfiguration(ParserFactory<T,D> parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Value("${content.configuration.type:'JSON_CONFIGURATION'}")
    private ContentType contentTypeConfiguration;

    @Value("${configuration.file.name:'configurations.json'}")
    private String configurationFileName;


    @Override
    public List<T> getAll() {

        String fileName = contentTypeConfiguration.fileName(configurationFileName);
        log.info("Fetching list from file {}", fileName);

        return parserFactory
                .getParser(contentTypeConfiguration)
                .parse(getFileHandle(fileName));
    }

    @Override
    public T get() {
        String fileName = contentTypeConfiguration.fileName(configurationFileName);
        log.info("Fetching list from file {}", fileName);

        return parserFactory
                .getParser(contentTypeConfiguration)
                .parseItem(getFileHandle(fileName));
    }


}
