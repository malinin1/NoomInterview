package com.example.noominterview.foodsearch.data

import com.example.noominterview.util.SchedulersProvider
import javax.inject.Inject

class FoodSearchRepository @Inject constructor(
    private val foodSearchApiService: FoodSearchApiService,
    private val schedulersProvider: SchedulersProvider) {

    
}