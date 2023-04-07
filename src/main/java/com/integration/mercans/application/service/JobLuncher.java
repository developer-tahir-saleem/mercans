package com.integration.mercans.application.service;

import com.integration.mercans.application.domain.JobDto;

/**
 * This interface defines a job launcher, which can start a job and return a DTO representing the
 * job's status and results.
 */
public interface JobLuncher {

  /**
   * Starts the job and returns a DTO representing the job's status and results.
   *
   * @return a JobDto representing the job's status and results
   */
  JobDto Start();
}
