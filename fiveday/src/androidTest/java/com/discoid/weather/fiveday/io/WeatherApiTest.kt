package com.discoid.weather.fiveday.io

import android.support.test.InstrumentationRegistry
import com.discoid.weather.fiveday.city.CityCodes
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class WeatherApiTest {

    lateinit var weatherApi: WeatherAPI
        @Inject set

    @Before
    fun setup() {
        var context = InstrumentationRegistry.getInstrumentation().context

        val testAppComponent = DaggerWeatherTestComponent.builder()
                .create(context)
                .build()
        testAppComponent.inject(this)

    }

    @Test
    fun checkForInstance() {
        checkNotNull(weatherApi)
    }

    @Test
    fun fetchLondonWeatherTest() {

        var londonCityCode = CityCodes.LONDON

        var testSubscriber = weatherApi.getFiveDayWeather(londonCityCode).test()

        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertNoErrors()

        assertEquals(1, testSubscriber.valueCount())
        var response = testSubscriber.values()[0]
        assertEquals(200, response.code())
        var weather  = response.body()

        assertEquals("London",  weather?.name)
    }

    @Test
    fun fetchDublinWeatherTest() {

        var cityCode = CityCodes.DUBLIN

        var testSubscriber = weatherApi.getFiveDayWeather(cityCode).test()

        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertNoErrors()

        assertEquals(1, testSubscriber.valueCount())
        var response = testSubscriber.values()[0]
        assertEquals(200, response.code())
        var weather  = response.body()

        assertEquals("Dublin",  weather?.name)
    }
}