package com.example.cattinder.data;

import com.example.cattinder.api.CatService;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Object returned from the {@link CatService}.
 *
 * Note: Gson is used by Retrofit and parses the response to
 * an object of this class.
 */
public class CatServiceResponse {
  private List<Cat> items = new ArrayList<>();

  public List<Cat> getCats() {
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

    public Uri getImageUri() {
      return Uri.parse(link);
    }

    public String getDescription() {
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
