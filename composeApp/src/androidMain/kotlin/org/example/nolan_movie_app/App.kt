package org.example.nolan_movie_app

import android.app.Application
import org.example.nolan_movie_app.di.androidModule
import org.example.nolan_movie_app.di.configModule
import org.example.nolan_movie_app.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                configModule,
                networkModule,
                platformModule,
                sharedModule,
                androidModule
            )
        }
    }
}