package com.example.meteoParis.Network

import com.example.meteoParis.Model.ForecastModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherApi {
    @GET("weatherdata")
    fun getLocationDetails(
        @Header("X-Mashape-Key") key: String, @Header("Accept") type: String,
        @Query("lat") lat: Double, @Query("lng") lng: Double
    ): Observable<ForecastModel>

}