package com.suncode.weatherapp.network;

import com.suncode.weatherapp.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/forecastrss")
    public Call<Weather> getWeather(
            @Query("location") String location,
            @Query("format") String format,
            @Query("u") String unit
            );
}
