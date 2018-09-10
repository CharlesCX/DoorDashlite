package com.charles.doordashlite.rest;

import com.charles.doordashlite.models.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GateWay {
    @GET("v2/restaurant/")
    Call<List<Restaurant>> getRestaurant(@Query("lat") Double lat,
                                         @Query("lng") Double lng,
                                         @Query("offset") int offset,
                                         @Query("limit") int limit);
}
