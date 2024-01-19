package com.example.noominterview.foodsearch.data

import com.example.noominterview.foodsearch.dagger.FoodSearchScope
import com.example.noominterview.util.SchedulersProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Response
import javax.inject.Inject

@FoodSearchScope
class FoodSearchRepository @Inject constructor(
    private val foodSearchApiService: FoodSearchApiService,
    private val schedulersProvider: SchedulersProvider) {
    fun searchForFood(searchQuery: String): Single<Response<List<FoodSearchResponse>>> {
        return Single.error(IllegalAccessError())
    }


}
