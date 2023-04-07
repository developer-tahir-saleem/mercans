package com.integration.mercans.application.service;

import com.integration.mercans.application.domain.JobDto;
import com.integration.mercans.configurations.domain.DynamicConfiguration;
import com.integration.mercans.jobs.domain.Job;
import com.integration.mercans.jobs.service.JobsService;
import java.util.Optional;

/**
 * This is a utility class for launching jobs. It provides a method to get the name of a file that
 * matches a given pattern in a directory.
 */
public class AbstractJobLauncher {

    /**
     * Gets the name of a file that matches the given pattern in the specified directory.
     *
     * @param csvJobsService  the service for managing CSV jobs
     * @param configuration   the dynamic configuration for the job
     * @param resourceDirectory the path to the directory where the file should be located
     * @return the name of the file that matches the pattern in the directory
     */
    public String getFileName(JobsService<Job, JobDto> csvJobsService, DynamicConfiguration configuration,String resourceDirectory) {
        Optional<String> fileName = csvJobsService.getFileNameByPatternFromDirectory(
            resourceDirectory,
            configuration.getFileNamePattern());
        return resourceDirectory + "/" + fileName.get();
    }
}
