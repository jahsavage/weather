package com.discoid.weather.fiveday.di.module

import com.discoid.weather.fiveday.city.CityActivity
import com.discoid.weather.fiveday.city.di.CityModule
import com.discoid.weather.fiveday.di.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by mertsimsek on 25/05/2017.
 */
@Module
abstract class ActivitiesBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(CityModule::class))
    abstract fun cityActivity(): CityActivity


}
