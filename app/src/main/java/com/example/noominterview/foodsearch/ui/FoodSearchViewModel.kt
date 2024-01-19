package com.example.noominterview.foodsearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noominterview.foodsearch.data.FoodSearchRepository
import com.example.noominterview.foodsearch.data.FoodSearchResponse
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class FoodSearchViewModel @Inject constructor(private val foodSearchRepository: FoodSearchRepository): ViewModel() {

    private val _foodSearchLiveData : MutableLiveData<FoodSearchViewState> = MutableLiveData()
    val foodSearchLiveData : LiveData<FoodSearchViewState> = _foodSearchLiveData

    private val disposable = CompositeDisposable()
    init {
        _foodSearchLiveData.value = FoodSearchLoading()
    }
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}

sealed class FoodSearchViewState
class FoodSearchLoading(): FoodSearchViewState()
data class FoodSearchError(val error: String): FoodSearchViewState()
data class FoodSearchSuccess(val results: List<FoodSearchResponse>): FoodSearchViewState()