package com.discoid.weather.fiveday.city.di;

import com.discoid.weather.fiveday.di.scopes.PerActivity;
import com.discoid.weather.fiveday.io.IWeatherInteractor;
import com.discoid.weather.fiveday.io.ResponseInterpreter;
import com.discoid.weather.fiveday.io.WeatherAPI;
import com.discoid.weather.fiveday.io.WeatherInteractor;
import com.discoid.weather.fiveday.city.CityPresenter;
import com.discoid.weather.fiveday.city.ICityPresenter;
import com.discoid.weather.fiveday.schedulers.IScheduler;

import dagger.Module;
import dagger.Provides;

@Module
public class CityModule {

    @Provides
    @PerActivity
    IWeatherInteractor provideWeatherInteractor(WeatherAPI weatherApi, IScheduler scheduler, ResponseInterpreter responseInterpreter ) {
        return new WeatherInteractor(weatherApi, scheduler, responseInterpreter);
    }

    @Provides
    @PerActivity
    ICityPresenter provideCityWeatherPresenter(IWeatherInteractor weatherInteractor) {
        return new CityPresenter(weatherInteractor);
    }


}
