package com.nidhin.newsapp

import android.app.Application
import android.content.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
    }

}
