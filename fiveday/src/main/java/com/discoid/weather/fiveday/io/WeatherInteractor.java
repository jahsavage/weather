package com.discoid.weather.fiveday.io;

import com.discoid.weather.fiveday.model.Weather;
import com.discoid.weather.fiveday.schedulers.IScheduler;

import io.reactivex.Single;
import retrofit2.Response;

public class WeatherInteractor implements IWeatherInteractor {

    private WeatherAPI weatherApi;
    private IScheduler scheduler;
    private ResponseInterpreter responseInterpreter;

    public WeatherInteractor(WeatherAPI weatherApi, IScheduler scheduler, ResponseInterpreter responseInterpreter) {
        this.weatherApi = weatherApi;
        this.scheduler = scheduler;
        this.responseInterpreter = responseInterpreter;
    }

    public Single<Weather> fetch(Integer cityCode) {
        return weatherApi.getFiveDayWeather(cityCode)
                .doOnSuccess(responseInterpreter::filterErrors)
                .map(Response::body)
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread());
    }
}
