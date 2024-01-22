package com.example.noominterview.foodsearch.dagger

import com.example.noominterview.application.dagger.ActivityScope
import com.example.noominterview.foodsearch.data.FoodSearchApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FoodSearchNetworkModule {
    @Provides
    @ActivityScope
    fun provideFoodSearchApiService(retrofit: Retrofit): FoodSearchApiService {
        return retrofit.create(FoodSearchApiService::class.java)
    }
}