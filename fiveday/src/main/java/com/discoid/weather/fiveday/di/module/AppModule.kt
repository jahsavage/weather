package com.discoid.weather.fiveday.di.module

import android.app.Application
import android.content.Context
import com.discoid.weather.fiveday.schedulers.IScheduler
import com.discoid.weather.fiveday.schedulers.LiveScheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by jahsavage on 10/08/2016.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideScheduler(): IScheduler {
        return LiveScheduler()
    }

}
