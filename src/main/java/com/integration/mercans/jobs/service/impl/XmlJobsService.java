package com.integration.mercans.jobs.service.impl;

import com.integration.mercans.jobs.service.AbstractJobs;
import com.integration.mercans.jobs.service.JobsService;
import com.integration.mercans.parsers.enums.ContentType;
import com.integration.mercans.parsers.factory.ParserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class XmlJobsService<T,D> extends AbstractJobs implements JobsService<T,D> {

    public static String LOGGER = "XmlJobsService: %s %s";
    @Override
    public Optional<String> getFileNameByPatternFromDirectory(String directoryName, String fileNamePattern) {
        log.info(String.format(LOGGER, "getFileNameByPatternFromDirectory", " not implemented"));
        return Optional.empty();
    }

    @Override
    public List<T> loadData(String fileName) {
        log.info(String.format(LOGGER, "loadData", " not implemented"));
        return null;
    }

    @Override
    public void prettyPrint(D data) {
        log.info(String.format(LOGGER, "prettyPrint", " not implemented"));
    }
}