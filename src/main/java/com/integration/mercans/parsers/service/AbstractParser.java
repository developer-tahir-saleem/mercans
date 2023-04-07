package com.integration.mercans.parsers.service;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * This class provides a collection of utility methods for working with Parser instances.
 * @author Tahir Saleem
 * @version 1.0
 */
public class AbstractParser {

  /**
   * Returns a Collector that collects the input elements into an ArrayList.
   *
   * @param <T> The type of input elements to be collected.
   * @return A Collector that collects the input elements into an ArrayList.
   */
  public static <T> Collector<T, ?, ArrayList<T>> toArrayList() {
    return Collectors.toCollection(ArrayList::new);
  }
}
