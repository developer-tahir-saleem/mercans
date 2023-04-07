package com.integration.mercans.parsers.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.integration.mercans.jobs.domain.exception.ParserIOException;
import com.integration.mercans.parsers.service.AbstractParser;
import com.integration.mercans.parsers.service.Parser;
import com.integration.mercans.parsers.service.TypeConstants;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component(TypeConstants.XML_JOB_PARSER)
@Slf4j
public class XMLJobParser<T,D> extends AbstractParser implements Parser<T,D> {

  private static XmlMapper _cached;
  public static String LOGGER = "XMLJobParser: %s %s";
  static {
    _cached = new XmlMapper();
  }

  @Override
  public List<T> parse(Reader r) {
    try {
      return _cached.readValue(r, new TypeReference<List<T>>() {
      });

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing XML file", e);
    }
  }

  @Override
  public T parseItem(Reader r) {
    return null;
  }

  @Override
  public List<T> parse(String payload) {
    try {
      return _cached.readValue(payload, new TypeReference<List<T>>() {
      });

    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing XML file", e);
    }
  }

  @Override
  public void prettyPrint(D data) {
    log.info(String.format(LOGGER, "prettyPrint", " not implemented"));
  }
}
