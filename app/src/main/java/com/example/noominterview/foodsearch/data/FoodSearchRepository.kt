package com.example.noominterview.foodsearch.data

import com.example.noominterview.application.dagger.ActivityScope
import com.example.noominterview.util.SchedulersProvider
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

@ActivityScope
class FoodSearchRepository @Inject constructor(
    private val foodSearchApiService: FoodSearchApiService,
    private val schedulersProvider: SchedulersProvider) {
    fun searchForFood(searchQuery: String): Single<Response<List<FoodSearchResponse>>> {
        return foodSearchApiService.search(searchQuery)
            .subscribeOn(schedulersProvider.getIOScheduler())
    }


}
