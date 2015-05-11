package com.cperry.cattinder.adapter;

import com.cperry.cattinder.api.CatImageService;
import com.cperry.cattinder.data.Cats.Cat;

import java.util.List;

public class KittyPaginator implements Paginator<List<Cat>> {
  private static final int PAGE_INCREMENT = 10;
  private int index = 1 - PAGE_INCREMENT;
  private final CatImageService service;

  public KittyPaginator(CatImageService service) {
    this.service = service;
  }

  @Override public List<Cat> getNextPage() {
    index += PAGE_INCREMENT;
    return service.getCats(index).get();
  }
}
