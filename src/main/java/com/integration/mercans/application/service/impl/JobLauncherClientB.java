package com.integration.mercans.application.service.impl;


import com.integration.mercans.application.domain.JobDto;
import com.integration.mercans.application.service.AbstractJobLauncher;
import com.integration.mercans.application.service.JobLuncher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "integration", name = "launcher", havingValue = "client-b")
public class JobLauncherClientB extends AbstractJobLauncher implements JobLuncher {

  public static String LOGGER = "JobLauncherClientB: %s %s";

  @Override
  public JobDto Start() {
    log.info(LOGGER, "JobLauncherClientB", " not Implemented");
    return null;
  }
}
