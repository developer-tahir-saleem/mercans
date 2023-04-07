package com.integration.mercans.parsers.service;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.List;


/**
 * A parser for data payloads that produces a list of objects of type T.
 *
 * This interface defines a common set of methods that can be used by different parsers to
 * parse input data and produce a list of parsed objects. The actual implementation of these
 * methods will depend on the specific parser being used, but they must conform to the signature
 * defined by this interface.
 *
 * @param <T> the type of object that this parser produces
 */
public interface Parser<T,D> {

  /**
   * Parses a stream of input data from the given Reader and produces
   * a list of parsed objects of type T.
   *
   * This method reads input data from the provided Reader and uses a parser implementation to parse
   * it into objects of type T. The parsed objects are then added to a List,
   * which is returned to the caller.
   *
   * @param r the Reader object containing the input data to be parsed
   * @return a List of parsed objects of type T
   * @throws IOException if an I/O error occurs while reading the input data
   * @throws ParseException if the input data cannot be parsed into objects of type T
   */
  List<T> parse(Reader r);

  /**
   * Parses a single item from the given Reader and returns a parsed object of type T.
   *
   * This method reads input data from the provided Reader and uses a parser implementation to parse
   * a single item into an object of type T. The parsed object is then returned to the caller.
   *
   * @param r the Reader object containing the input data to be parsed
   * @return a parsed object of type T
   * @throws IOException if an I/O error occurs while reading the input data
   * @throws ParseException if the input data cannot be parsed into an object of type T
   */
  T parseItem(Reader r);

  /**
   * Parses the given input data as a String and returns a list of parsed objects of type T.
   *
   * This method uses a parser implementation to parse the input data into a list of objects of type T.
   * The parsed objects are then returned to the caller.
   *
   * @param payload the input data to be parsed, represented as a String
   * @return a list of parsed objects of type T
   * @throws ParseException if the input data cannot be parsed into objects of type T
   */
  List<T> parse(String payload);

  void  prettyPrint(D data);

}