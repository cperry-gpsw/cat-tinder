package com.cperry.cattinder.api;

import android.util.Log;

import retrofit.RestAdapter;

public class ServiceFactoryImpl implements ServiceFactory {

  public CatImageService getCatImageService() {
    RestAdapter restAdapter = new RestAdapter.Builder()
      .setEndpoint("https://www.googleapis.com")
      .setLogLevel(RestAdapter.LogLevel.FULL)
      .setLog(message -> Log.d("Retrofit", message))
      .build();

    return restAdapter.create(CatImageService.class);
  }
}
