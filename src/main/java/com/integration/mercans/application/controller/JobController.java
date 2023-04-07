package com.integration.mercans.application.controller;

import com.integration.mercans.application.domain.JobDto;
import com.integration.mercans.application.service.JobLuncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a Spring REST controller for managing jobs. It provides an endpoint for getting a job's
 * status and results for a specific client.
 *
 * @RestController annotation indicates that this class is a Spring REST controller.
 * @RequestMapping annotation maps HTTP requests to handler methods of this controller.
 * It specifies the base URL for all requests handled by this controller and the media type
 * of the response.
 */
@RestController
@RequestMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobController {


  /**
   * The job launcher client for executing jobs for client A.
   */
  @Autowired
  JobLuncher jobLauncherClientA;


  /**
   * Retrieves the status and results of a job for client A.
   *
   * @return a ResponseEntity containing a JobDto representing the job's status and results
   */
  @GetMapping("/client-a")
  public ResponseEntity<JobDto> getJobByClient() {
    return ResponseEntity.ok().body(jobLauncherClientA.Start());
  }
}