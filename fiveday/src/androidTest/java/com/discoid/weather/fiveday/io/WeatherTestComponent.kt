package com.discoid.weather.fiveday.io

import android.content.Context
import com.discoid.weather.fiveday.di.module.WeatherAPIModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(WeatherAPIModule::class))
interface WeatherTestComponent {

    fun inject(test: WeatherApiTest)
    fun inject(test: WeatherInteractorTest)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(context: Context): Builder
        fun build(): WeatherTestComponent
    }

}
