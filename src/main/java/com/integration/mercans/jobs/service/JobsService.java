package com.integration.mercans.jobs.service;


import java.util.List;
import java.util.Optional;


/**
 * This interface defines a set of methods for interacting with job data.
 *
 * @param <T> The type of the job data.
 */
public interface JobsService<T,D> {

  /**
   * Searches a directory for files matching a given pattern, and returns the name of the first match.
   *
   * @param directoryName The name of the directory to search.
   * @param fileNamePattern The pattern to match against file names.
   * @return An optional containing the name of the first matching file, or empty if no match was found.
   */
  Optional<String> getFileNameByPatternFromDirectory(String directoryName, String fileNamePattern);

  /**
   * Loads job data from a file.
   *
   * @param fileName The name of the file to load.
   * @return A list of job data loaded from the file.
   */
  List<T> loadData(String fileName);
  void  prettyPrint(D data);
}