package com.cperry.cattinder.api;

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
          "http://truestorieswithgill.com/wp-content/uploads/2013/09/20130915-190532.jpg",
          "Grumpy Cat"
        ),
        new Cats.Cat(
          "https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-xaf1/v/t1" +
            ".0-9/304276_294451973899431_1913235174_n" +
            ".jpg?oh=2b4d96f7f49d45b572902cfd346c78a7&oe=55D6655C&__gda__" +
            "=1438927245_cf2c5b0a5881ae9029812edef6e4c32b",
          "Pirate Cat"
        ),
        new Cats.Cat(
          "http://static.giantbomb.com/uploads/scale_small/4/45471/1137644-ceiling_cat_900.jpg.png",
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
  }

}
