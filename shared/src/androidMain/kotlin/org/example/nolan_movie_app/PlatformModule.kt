package org.example.nolan_movie_app

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val platformModule = module {
    single<Settings> {
        val context = androidContext()
        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        SharedPreferencesSettings(prefs)
    }
}