package com.discoid.weather.fiveday.io

import com.discoid.weather.fiveday.model.Weather
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("data/2.5/weather?appid=6015fc84ddcae3899a4a256a8877d25f")
    fun getFiveDayWeather(@Query("id") cityId : Int) : Single<Response<Weather>>

}