package com.example.noominterview.application

import android.app.Application
import com.example.noominterview.application.dagger.ApplicationComponent
import com.example.noominterview.application.dagger.DaggerApplicationComponent

class NoomApplication: Application() {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent

        fun getApplicationComponent(): ApplicationComponent {
            return applicationComponent
        }
    }

    override fun onCreate() {
        initDaggerComponent()
        super.onCreate()
    }

    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .bindApplication(this)
            .build()
    }
}