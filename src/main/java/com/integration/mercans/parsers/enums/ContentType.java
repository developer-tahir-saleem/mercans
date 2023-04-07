package com.integration.mercans.parsers.enums;

import static com.integration.mercans.parsers.service.TypeConstants.CSV_JOB_PARSER;
import static com.integration.mercans.parsers.service.TypeConstants.JSON_CONFIGURATION_PARSER;
import static com.integration.mercans.parsers.service.TypeConstants.JSON_JOB_PARSER;
import static com.integration.mercans.parsers.service.TypeConstants.XML_JOB_PARSER;

/**
 * This enum represents the different types of content that can be stored in a database.
 * Each content type has a unique identifier that is used to distinguish it from other types.
 *
 * @author Tahir Saleem
 * @version 1.0
 */
public enum ContentType {

  JSON_JOB(JSON_JOB_PARSER) {
    @Override
    public String fileName(String name) {
      return name;
    }
  },
  JSON_CONFIGURATION(JSON_CONFIGURATION_PARSER) {
    @Override
    public String fileName(String name) {
      return name;
    }
  },
  CSV_JOB(CSV_JOB_PARSER) {
    @Override
    public String fileName(String name) {
      return name;
    }
  },
  XML_JOB(XML_JOB_PARSER) {
    @Override
    public String fileName(String name) {
      return name;
    }
  };

  private final String parserName;

  ContentType(String parserName) {
    this.parserName = parserName;
  }

  public abstract String fileName(String name);

  @Override
  public String toString() {
    return parserName;
  }


}