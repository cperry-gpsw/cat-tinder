package com.cperry.cattinder.data;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class Cats {
  private List<Cat> items = new ArrayList<>();

  public List<Cat> get() {
    return items;
  }

  public static class Cat {
    private Uri uri;
    private String snippet;

    public Cat() {
      uri = Uri.EMPTY;
      snippet = "";
    }

    public Cat(Uri uri, String snippet) {
      this.uri = uri;
      this.snippet = snippet;
    }

    public Uri getUri() {
      return uri;
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
      return uri.equals(cat.uri) && snippet.equals(cat.snippet);

    }

    @Override public int hashCode() {
      int result = uri.hashCode();
      result = 31 * result + snippet.hashCode();
      return result;
    }
  }
}
