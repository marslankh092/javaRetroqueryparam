package com.example.fragments.server;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//https://www.omdbapi.com/?apikey=83b533f9&s=movie&page=1

public interface BaseApiService {

    @GET(".")
    Call<ModelClassMovie> readData (@Query("apikey") String apikey,
                                     @Query("s") String s,
                           @Query("page") int page) ;

}
