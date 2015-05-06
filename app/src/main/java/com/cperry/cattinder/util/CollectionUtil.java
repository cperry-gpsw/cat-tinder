package com.cperry.cattinder.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtil {

  public static <T> List<T> newList(T... items) {
    ArrayList<T> list = new ArrayList<>();
    Collections.addAll(list, items);
    return list;
  }
}
