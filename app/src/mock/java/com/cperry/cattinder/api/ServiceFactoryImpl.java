package com.cperry.cattinder.api;

import android.net.Uri;

import com.cperry.cattinder.data.Cats;
import com.cperry.cattinder.util.CollectionUtil;

import java.util.List;

public class ServiceFactoryImpl implements ServiceFactory {

  @Override public CatImageService getCatImageService() {
    return new MockCatImageService();
  }

  private static class MockCatImageService implements CatImageService {

    @Override public Cats getCats(int startIndex) {
      return getCatsForIndex(startIndex);
    }

    private Cats getCatsForIndex(int startIndex) {
      return new Cats() {
        @Override public List<Cat> get() {
          return CollectionUtil.newList(
            new MockCat(
              "grumpy_cat",
              "Grumpy Cat " + startIndex
            ),
            new MockCat(
              "pirate_cat",
              "Pirate Cat " + (startIndex + 1)
            ),
            new MockCat(
              "ceiling_cat",
              "Ceiling Cat " + (startIndex + 2)
            )
          );
        }
      };
    }

    private static class MockCat extends Cats.Cat {

      public MockCat(String link, String snippet) {
        super(link, snippet);
      }

      @Override public Uri getUri() {
        return Uri.parse("android.resource://com.cperry.cattinder/drawable/" + this.link);
      }
    }
  }

}
