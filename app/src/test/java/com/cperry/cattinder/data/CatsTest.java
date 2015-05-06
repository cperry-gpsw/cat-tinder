package com.cperry.cattinder.data;

import com.cperry.cattinder.data.Cats.Cat;
import com.google.gson.Gson;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.fest.assertions.Assertions.assertThat;

public class CatsTest {

  @Test
  public void gsonShouldParseToCats() throws FileNotFoundException {
    // Given
    String json = "{\n" +
      "  \"items\": [\n" +
      "    {\n" +
      "      \"link\": \"http://some.com/cat_one.jpg\",\n" +
      "      \"snippet\": \"Cat 1\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"link\": \"http://some.com/cat_two.jpg\",\n" +
      "      \"snippet\": \"Cat 2\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"link\": \"http://some.com/cat_three.jpg\",\n" +
      "      \"snippet\": \"Cat 3\"\n" +
      "    }\n" +
      "  ]\n" +
      "}";

    // When
    Cats cats = new Gson().fromJson(json, Cats.class);

    // Then
    assertThat(cats.get()).containsExactly(
      new Cat("http://some.com/cat_one.jpg", "Cat 1"),
      new Cat("http://some.com/cat_two.jpg", "Cat 2"),
      new Cat("http://some.com/cat_three.jpg", "Cat 3")
    );
  }

}