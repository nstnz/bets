package dev.nst.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BetsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}