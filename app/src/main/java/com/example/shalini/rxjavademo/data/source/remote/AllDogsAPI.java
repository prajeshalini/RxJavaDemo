package com.example.shalini.rxjavademo.data.source.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Shalini Prajesh on 13/2/18.
 */

public interface AllDogsAPI {

    @GET("api/breeds/list/all")
    Observable<DogsBreedList> getAllDogsBreed();

    @GET(".")
    Observable<AuthorDetails> getAuthorDetails();
}
