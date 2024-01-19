package com.example.noominterview.foodsearch.dagger

import com.example.noominterview.foodsearch.data.FoodSearchApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FoodSearchNetworkModule {
    @Provides
    @FoodSearchScope
    fun provideFoodSearchApiService(retrofit: Retrofit): FoodSearchApiService {
        return retrofit.create(FoodSearchApiService::class.java)
    }
}