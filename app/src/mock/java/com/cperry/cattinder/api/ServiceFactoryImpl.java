package com.cperry.cattinder.api;

import android.net.Uri;

import com.cperry.cattinder.data.Cats;
import com.cperry.cattinder.util.CollectionUtil;

import java.util.List;

import rx.Observable;

public class ServiceFactoryImpl implements ServiceFactory {

  @Override public CatImageService getCatImageService() {
    return new MockCatImageService();
  }

  private static class MockCatImageService implements CatImageService {

    @Override public Observable<Cats> getCats() {

      List<Cats.Cat> list = CollectionUtil.newList(
        new Cats.Cat(
          getUri("grumpy_cat"),
          "Grumpy Cat"
        ),
        new Cats.Cat(
          getUri("pirate_cat"),
          "Pirate Cat"
        ),
        new Cats.Cat(
          getUri("ceiling_cat"),
          "Ceiling Cat"
        )
      );

      Cats cats = new Cats() {
        @Override public List<Cat> get() {
          return list;
        }
      };

      return Observable.from(new Cats[]{ cats });
    }

    Uri getUri(String resource) {
      return Uri.parse("android.resource://com.cperry.cattinder/drawable/" + resource);
    }
  }

}
