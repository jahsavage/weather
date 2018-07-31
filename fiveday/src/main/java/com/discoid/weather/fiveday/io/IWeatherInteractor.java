package com.discoid.weather.fiveday.io;

import com.discoid.weather.fiveday.model.Weather;

import io.reactivex.Single;

public interface IWeatherInteractor {
    Single<Weather> fetch(Integer cityCode);
}
