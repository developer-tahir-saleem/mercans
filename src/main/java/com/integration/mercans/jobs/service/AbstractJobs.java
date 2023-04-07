package com.integration.mercans.jobs.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * This is an abstract base class for jobs that need to read data from files. It provides a method
 * to get a Reader object for a given file name.
 */
public class AbstractJobs {

  /**
   * Returns a Reader object for the specified file name. The file must be on the classpath.
   *
   * @param fileName the name of the file to read from
   * @return a Reader object that reads from the specified file
   */
  protected Reader getFileHandle(String fileName) {
    InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
    return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
  }
}
