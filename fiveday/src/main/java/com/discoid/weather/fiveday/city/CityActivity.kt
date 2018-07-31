package com.discoid.weather.fiveday.city

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.discoid.weather.fiveday.R
import com.discoid.weather.fiveday.model.Weather
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.content_weather.*
import javax.inject.Inject

class CityActivity : AppCompatActivity(), ICityPresenterView {

    @Inject
    lateinit var cityPresenter : ICityPresenter

    var currentLocIndex = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)
        fbLocationIterator.setOnClickListener { view ->
            currentLocIndex++
            if (currentLocIndex >= CityCodes.CITY_LIST.size) {
                currentLocIndex = 0
            }
            cityPresenter.updateCityCode(CityCodes.CITY_LIST[currentLocIndex])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_weather, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun display(weather: Weather) {
        var newTitle =  "${weather.name}?"

        tvLocation.text = newTitle

        if (weather.name == "London") {
            ivWeatherBg.setImageResource(R.drawable.sunny)
        } else if (weather.name == "Dublin") {
            ivWeatherBg.setImageResource(R.drawable.showers)
        } else {
            ivWeatherBg.setImageResource(R.drawable.sunny_with_showers)
        }

        tvWindSpeed.text = "Wind speed ${weather.wind.speed} msec"

        var desc = weather.weather.get(0).description ?: "Stay at home!"
        tvClouds.text = desc
        tvTemperature.text = "Temperture \nHigh ${weather.main.tempMax} \n" +
                "Low ${weather.main.tempMin} "

        tvSunriseSunset.text = "Sunrise \n${weather.sys.sunrise} \nSunset \n ${weather.sys.sunset}"
    }

    override fun onResume() {
        super.onResume()
        cityPresenter.start(this, CityCodes.CITY_LIST[currentLocIndex]);
    }

    override fun onDestroy() {
        super.onDestroy()
        cityPresenter.close()
    }

}
