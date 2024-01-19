package com.example.noominterview.foodsearch.data

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodSearchApiService {

    @GET("dev/search")
    fun search(@Query("kv") query: String): Single<Response<List<FoodSearchResponse>>>
}