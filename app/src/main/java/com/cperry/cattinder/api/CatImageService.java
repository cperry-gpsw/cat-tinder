package com.cperry.cattinder.api;

import com.cperry.cattinder.data.Cats;

import retrofit.http.GET;
import rx.Observable;

/**
 * Uses Google Custom Search API to search
 * for images of cats.
 *
 * Key: AIzaSyAZmvW6DecHAvtLCiqYQzmhKCNnsOYxtgo
 * Search Engine ID: 005351716643766109453:uusxxukdams
 *
 * URL:
 * https://www.googleapis.com/customsearch/v1?key=AIzaSyAZmvW6DecHAvtLCiqYQzmhKCNnsOYxtgo&cx=005351716643766109453:uusxxukdams&q=cat&searchType=image
 */
public interface CatImageService {

  @GET("/customsearch/v1?key=AIzaSyAZmvW6DecHAvtLCiqYQzmhKCNnsOYxtgo&cx=005351716643766109453" +
    ":uusxxukdams&searchType=image&q=cat")
  Observable<Cats> getCats();
}
