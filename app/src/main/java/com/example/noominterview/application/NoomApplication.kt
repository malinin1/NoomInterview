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

        // Somewhat contrived to have subcomponents with such a small application,
        // but added as an example of how to split up Dagger components as the application scales
        // In a real application, we would need to manage releasing the subcomponent when
        // navigating away from its scope.
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