package com.example.noominterview.application

import android.app.Application
import com.example.noominterview.application.dagger.ApplicationComponent
import com.example.noominterview.application.dagger.DaggerApplicationComponent
import com.example.noominterview.foodsearch.dagger.FoodSearchComponent
import com.example.noominterview.foodsearch.dagger.FoodSearchNetworkModule

class NoomApplication: Application() {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent
        private var foodSearchComponent: FoodSearchComponent? = null

        fun getApplicationComponent(): ApplicationComponent {
            return applicationComponent
        }

        // TODO Explain scopes/lifecycles
        fun getFoodSearchComponent(): FoodSearchComponent {
            foodSearchComponent?.let {
                return it
            }
            val foodSearchComponent = applicationComponent
                .foodSearchComponent()
                .networkModule(FoodSearchNetworkModule())
                .build()
            this.foodSearchComponent = foodSearchComponent
            return foodSearchComponent
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