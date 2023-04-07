package com.integration.mercans.parsers.service.impl;

import static java.util.stream.Collectors.toList;

import com.integration.mercans.jobs.domain.Job;
import com.integration.mercans.jobs.domain.exception.ParserIOException;
import com.integration.mercans.parsers.service.Parser;
import com.integration.mercans.parsers.service.TypeConstants;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.simpleflatmapper.csv.CsvParser;
import org.springframework.stereotype.Component;

@Slf4j
@Component(TypeConstants.CSV_JOB_PARSER)
public class CSVJobParser<T, D> implements Parser<T, D> {

  private static final CsvParser.MapToDSL<Job> _cached;
  public static String LOGGER = "CSVJobParser: %s %s";

  static {
    _cached = CsvParser
        .separator(',')
        .trimSpaces()
        .mapTo(Job.class);
  }

  @Override
  public List<T> parse(Reader r) {
    try {
      return (List<T>) _cached.stream(r).collect(toList());
    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing CSV file", e);
    }
  }

  @Override
  public T parseItem(Reader r) {
    return null;
  }

  @Override
  public List<T> parse(String payload) {
    try {
      return (List<T>) _cached.stream(payload).collect(toList());
    } catch (IOException e) {
      throw new ParserIOException("Io error while parsing CSV file", e);
    }
  }

  @Override
  public void prettyPrint(D data) {
    log.info(String.format(LOGGER, "prettyPrint", " not implemented"));
  }
}