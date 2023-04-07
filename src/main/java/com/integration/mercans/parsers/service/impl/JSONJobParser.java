package com.integration.mercans.parsers.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.integration.mercans.jobs.domain.exception.ParserIOException;
import com.integration.mercans.parsers.service.AbstractParser;
import com.integration.mercans.parsers.service.Parser;
import com.integration.mercans.parsers.service.TypeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component(TypeConstants.JSON_JOB_PARSER)
@Slf4j
public class JSONJobParser<T,D> extends AbstractParser implements Parser<T,D> {

  private static ObjectMapper _cached;

  static {
    _cached = new ObjectMapper();
  }

  @Override
  public List<T> parse(Reader r) {
    try {
      return _cached.readValue(r, new TypeReference<>() {
      });

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing JSON file", e);
    }
  }

  @Override
  public T parseItem(Reader r) {
    try {
      return _cached.readValue(r, new TypeReference<>() {
      });

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing JSON file", e);
    }
  }

  @Override
  public List<T> parse(String payload) {
    try {
      return _cached.readValue(payload, new TypeReference<>() {
      });

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing JSON file", e);
    }
  }

  @Override
  public void prettyPrint(D data) {
    _cached.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    try {
      // Java objects to JSON string - pretty-print
      log.info(_cached.writerWithDefaultPrettyPrinter().writeValueAsString(data));
    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing JSON file", e);
    }
  }
}