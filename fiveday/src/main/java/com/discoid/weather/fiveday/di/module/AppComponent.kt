package com.discoid.weather.fiveday.di.module

import android.app.Application
import com.discoid.weather.fiveday.WeatherApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by jahsavage on 10/08/2016.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        WeatherAPIModule::class,
        ActivitiesBindingModule::class,
        AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<WeatherApp> {

    override fun inject(app: WeatherApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application):Builder
        fun build(): AppComponent
    }
}
