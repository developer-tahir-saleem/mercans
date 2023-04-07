package com.integration.mercans.parsers.service;

/**
 * This interface defines constants that represent the different types of parsers used in the application.
 *
 * The constants are used to identify and configure specific parser implementations used in the application.
 */
public interface TypeConstants {

  /**
   * Constant representing a CSV job parser implementation.
   */
  String CSV_JOB_PARSER = "csvJobParser";

  /**
   * Constant representing a JSON job parser implementation.
   */
  String JSON_JOB_PARSER = "jsonJobParser";

  /**
   * Constant representing an XML job parser implementation.
   */
  String XML_JOB_PARSER = "xmlJobParser";

  /**
   * Constant representing a JSON configuration parser implementation.
   */
  String JSON_CONFIGURATION_PARSER = "jsonConfigurationParser";
}