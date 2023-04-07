package com.integration.mercans.parsers.factory;

import com.integration.mercans.parsers.enums.ContentType;
import com.integration.mercans.parsers.service.Parser;

/**
 * This interface defines a factory for creating Parser instances of a given type.
 * The factory is responsible for looking up the appropriate Parser implementation based on a content type.
 *
 * @param <T> The type of object that the Parser produces.
 * @version 1.0
 */
public interface ParserFactory<T,D> {

  /**
   * Returns a Parser instance that is capable of parsing content of the given type.
   *
   * @param contentType The content type to be parsed.
   * @return A Parser instance that is capable of parsing content of the given type.
   */
  Parser<T,D> getParser(ContentType contentType);
}
