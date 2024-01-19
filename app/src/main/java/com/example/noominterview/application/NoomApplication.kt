package com.example.noominterview.application

import android.app.Application
import com.example.noominterview.application.dagger.ApplicationComponent
import com.example.noominterview.application.dagger.DaggerApplicationComponent
import com.example.noominterview.foodsearch.dagger.FoodSearchComponent
import com.example.noominterview.foodsearch.dagger.FoodSearchNetworkModule

class NoomApplication: Application() {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent

        fun getApplicationComponent(): ApplicationComponent {
            return applicationComponent
        }
    }

    private var foodSearchComponent: FoodSearchComponent? = null
    override fun onCreate() {
        initDaggerComponent()
        super.onCreate()
    }

    fun getOrCreateFoodSearchComponent(): FoodSearchComponent {
        synchronized(this) {
            return foodSearchComponent?.let {
                it
            } ?: kotlin.run {
                val foodSearchComponent = applicationComponent
                    .foodSearchComponent()
                    .networkModule(FoodSearchNetworkModule())
                    .build()
                this.foodSearchComponent = foodSearchComponent
                foodSearchComponent
            }
        }
    }

    fun releaseFoodSearchComponent() {
        synchronized(this) {
            this.foodSearchComponent = null
        }
    }
    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .bindApplication(this)
            .build()
    }
}