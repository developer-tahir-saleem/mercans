package com.integration.mercans.jobs.service.impl;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.integration.mercans.jobs.service.AbstractJobs;
import com.integration.mercans.parsers.enums.ContentType;
import com.integration.mercans.parsers.factory.ParserFactory;
import com.integration.mercans.jobs.service.JobsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CsvJobsService<T,D> extends AbstractJobs implements JobsService<T,D> {

    public static String LOGGER = "CsvJobsService: %s %s";
    private ParserFactory<T,D> parserFactory;

    @Autowired
    ResourceLoader resourceLoader;

    @Value("${app.example.enum-value:'CSV_JOB'}")
    private ContentType contentTypeJob;

    @Autowired
    public CsvJobsService(ParserFactory<T,D> parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    public Optional<String> getFileNameByPatternFromDirectory(String directoryName, String fileNamePattern) {
        return Optional.of(getFileName(directoryName,fileNamePattern));
    }

    @Override
    public List<T> loadData(String fileName) {
        log.info("Fetching list from file {}", fileName);
        return parserFactory
                .getParser(contentTypeJob)
                .parse(getFileHandle(fileName));
    }

    @Override
    public void prettyPrint(D data) {
        parserFactory
            .getParser(ContentType.JSON_JOB)
            .prettyPrint(data);
    }

    String getFileName(String directoryName, String fileNamePattern){
        try {
            Resource[] resources = loadResources("classpath:"+directoryName+"/*");
            for (Resource resource : resources) {
                if (resource.getFilename().matches(fileNamePattern)) {
                    log.info(resource.getFile().getName() + " Found Successfully");
                    return resource.getFile().getName();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileNamePattern;
    }

    Resource[] loadResources(String pattern) throws IOException {
        return ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(pattern);
    }

}