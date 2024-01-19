package com.example.noominterview.foodsearch.dagger

import com.example.noominterview.foodsearch.data.FoodSearchApiService
import com.example.noominterview.util.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class FoodSearchNetworkModule {
    @Provides
    @FoodSearchScope
    fun provideFoodSearchApiService(): FoodSearchApiService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()
        return retrofit.create(FoodSearchApiService::class.java)
    }
}