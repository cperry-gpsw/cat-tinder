package com.cperry.cattinder.adapter;

import com.cperry.cattinder.api.CatImageService;
import com.cperry.cattinder.data.Cats;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.Query;

import static org.fest.assertions.Assertions.assertThat;

public class KittyPaginatorTest {

  @Test
  public void paginates_startingAtIndexOne_incrementedByTen() {
    // Given
    SpyImageService service = new SpyImageService();
    KittyPaginator paginator = new KittyPaginator(service);

    // When
    paginator.getNextPage(); // 1
    paginator.getNextPage(); // 1 + 10  = 11
    paginator.getNextPage(); // 11 + 10 = 21
    paginator.getNextPage(); // 21 + 10 = 31

    // Then
    assertThat(service.getIndices()).containsExactly(1, 11, 21, 31);
  }

  static class SpyImageService implements CatImageService {
    private final List<Integer> indices = new ArrayList<>();

    @Override public Cats getCats(@Query("start") int startIndex) {
      indices.add(startIndex);
      return new Cats();
    }

    public List<Integer> getIndices() {
      return indices;
    }
  }

}