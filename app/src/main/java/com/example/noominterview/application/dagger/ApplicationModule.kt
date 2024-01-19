package com.example.noominterview.application.dagger

import android.content.Context
import com.example.noominterview.application.NoomApplication
import dagger.Module
import javax.inject.Singleton

@Module
class ApplicationModule constructor(private val application: NoomApplication) {

    @Singleton
    fun getApplication(): NoomApplication {
        return application
    }

    @Singleton
    fun getApplicationContext(): Context {
        return application.applicationContext
    }
}