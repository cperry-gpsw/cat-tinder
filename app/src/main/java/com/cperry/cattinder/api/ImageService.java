package com.cperry.cattinder.api;

import com.cperry.cattinder.data.Cats;

import retrofit.http.GET;
import rx.Observable;

public interface ImageService {

  @GET("/customsearch/v1?key=AIzaSyAZmvW6DecHAvtLCiqYQzmhKCNnsOYxtgo&cx=005351716643766109453" +
    ":uusxxukdams&searchType=image&q=cat")
  Observable<Cats> getCats();
}
