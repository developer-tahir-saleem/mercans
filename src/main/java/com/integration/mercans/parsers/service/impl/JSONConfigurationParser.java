package com.integration.mercans.parsers.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.mercans.configurations.domain.DynamicConfiguration;
import com.integration.mercans.jobs.domain.exception.ParserIOException;
import com.integration.mercans.parsers.service.AbstractParser;
import com.integration.mercans.parsers.service.Parser;
import com.integration.mercans.parsers.service.TypeConstants;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(TypeConstants.JSON_CONFIGURATION_PARSER)
public class JSONConfigurationParser<T,D> extends AbstractParser implements Parser<T,D> {

  public static String LOGGER = "JSONConfigurationParser: %s %s";
  private static ObjectMapper _cached;

  static {
    _cached = new ObjectMapper();
  }

  @Override
  public List<T> parse(Reader r) {
    try {
      return (List<T>) _cached.readValue(r, new TypeReference<List<DynamicConfiguration>>(){});

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing JSON file", e);
    }
  }

  @Override
  public T parseItem(Reader r) {
    try {
      return (T) _cached.readValue(r, new TypeReference<DynamicConfiguration>() {});

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
    log.info(String.format(LOGGER, "prettyPrint", " not implemented"));
  }
}