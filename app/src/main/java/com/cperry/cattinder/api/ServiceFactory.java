package com.cperry.cattinder.api;

import retrofit.RestAdapter;

public class ServiceFactory {

  public static ImageService getCatImageService() {
    RestAdapter restAdapter = new RestAdapter.Builder()
      .setEndpoint("https://www.googleapis.com")
      .build();

    return restAdapter.create(ImageService.class);
  }
}
