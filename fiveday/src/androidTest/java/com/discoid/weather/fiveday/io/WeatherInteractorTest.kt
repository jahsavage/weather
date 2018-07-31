package com.discoid.weather.fiveday.io

import android.support.test.InstrumentationRegistry
import com.discoid.weather.fiveday.city.CityCodes
import com.discoid.weather.fiveday.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class WeatherInteractorTest {

    val testScheduler = TestScheduler()

    lateinit var responseInterpreter: ResponseInterpreter
        @Inject set

    lateinit var weatherApi: WeatherAPI
        @Inject set

    lateinit var weatherInteractor : WeatherInteractor

    @Before
    fun setup() {
        var context = InstrumentationRegistry.getInstrumentation().context

        val testAppComponent = DaggerWeatherTestComponent.builder()
                .create(context)
                .build()
        testAppComponent.inject(this)

        weatherInteractor = WeatherInteractor(weatherApi, testScheduler, responseInterpreter)
    }

    @Test
    fun checkForInstance() {
        checkNotNull(weatherInteractor)
    }

    @Test
    fun fetchWeatherTest() {

        var londonCityCode = CityCodes.LONDON

        var testSubscriber = weatherInteractor.fetch(londonCityCode).test()

        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertNoErrors()

        assertEquals(1, testSubscriber.valueCount())
        var weather = testSubscriber.values()[0]

        assertEquals("London",  weather?.name)
    }

}