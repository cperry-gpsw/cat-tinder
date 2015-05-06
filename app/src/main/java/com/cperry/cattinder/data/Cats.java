package com.cperry.cattinder.data;

import java.util.ArrayList;
import java.util.List;

public class Cats {
  private List<Cat> items = new ArrayList<>();

  public List<Cat> get() {
    return items;
  }

  public static class Cat {
    private String link;
    private String snippet;

    public Cat() {
      link = "";
      snippet = "";
    }

    public Cat(String link, String snippet) {
      this.link = link;
      this.snippet = snippet;
    }

    public String getLink() {
      return link;
    }

    public String getSnippet() {
      return snippet;
    }

    @Override public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Cat cat = (Cat) o;
      return link.equals(cat.link) && snippet.equals(cat.snippet);

    }

    @Override public int hashCode() {
      int result = link.hashCode();
      result = 31 * result + snippet.hashCode();
      return result;
    }
  }
}
