package com.example.noominterview.foodsearch.dagger

import com.example.noominterview.foodsearch.ui.FoodSearchFragment
import dagger.Subcomponent
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class FoodSearchScope

@FoodSearchScope
@Subcomponent(modules = [FoodSearchNetworkModule::class])
interface FoodSearchComponent {

    @Subcomponent.Builder
    interface Builder {
        fun networkModule(module: FoodSearchNetworkModule): Builder
        fun build(): FoodSearchComponent
    }

    fun inject(fragment: FoodSearchFragment)
}