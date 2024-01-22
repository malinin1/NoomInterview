package com.example.noominterview.foodsearch.dagger

import com.example.noominterview.application.dagger.ActivityScope
import com.example.noominterview.foodsearch.ui.FoodSearchFragment
import dagger.Subcomponent


@ActivityScope
@Subcomponent(modules = [FoodSearchNetworkModule::class])
interface FoodSearchComponent {

    @Subcomponent.Builder
    interface Builder {
        fun networkModule(module: FoodSearchNetworkModule): Builder
        fun build(): FoodSearchComponent
    }

    fun inject(fragment: FoodSearchFragment)
}