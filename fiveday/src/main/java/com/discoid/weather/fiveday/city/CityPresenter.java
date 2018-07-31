package com.discoid.weather.fiveday.city;

import com.discoid.weather.fiveday.io.IWeatherInteractor;
import com.discoid.weather.fiveday.model.Weather;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class CityPresenter implements ICityPresenter {

    private ICityPresenterView view;
    private IWeatherInteractor weatherInteractor;
    private CompositeDisposable compositeDisposable;

    public CityPresenter(IWeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void start(ICityPresenterView view, Integer cityCode) {
        this.view = view;
        fetchCityWeather(cityCode);
    }

    @Override
    public void updateCityCode(Integer cityCode) {
        fetchCityWeather(cityCode);
    }

    @Override
    public void close() {
        compositeDisposable.clear();
        weatherInteractor = null;
        view = null;
    }

    private void fetchCityWeather(Integer cityCode) {
        compositeDisposable.add(weatherInteractor.fetch(cityCode)
                                    .subscribe(this::show, this::handleError));
    }

    private void show(Weather weather) {

        Timber.d("Receiver weather for %s", weather.getName());
        view.display(weather);
    }

    private void handleError(Throwable t) {
        Timber.e("Fetch weather exception %s", t.getMessage());
    }

}
