package com.example.travelapp.api;

import com.example.travelapp.model.Tour;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/tours/1/9")
    Call<Tour> getTours();


}
