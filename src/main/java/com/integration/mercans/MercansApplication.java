package com.integration.mercans;

import com.integration.mercans.application.service.JobLuncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MercansApplication implements CommandLineRunner {

  @Autowired
  JobLuncher jobLuncher;

  @Value("${launch.mode:''}")
  private String launchMode;

  public static void main(String[] args) {
    SpringApplication.run(MercansApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
//    Start Application when the profile console get output in console
    if (this.launchMode.equalsIgnoreCase("console")) {
      jobLuncher.Start();
    }
  }
}
