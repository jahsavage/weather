package com.discoid.weather.fiveday.city;

public interface ICityPresenter {

    void start(ICityPresenterView view, Integer cityCode);
    void updateCityCode(Integer cityCode);
    void close();
}
