package com.example.noominterview.foodsearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noominterview.foodsearch.data.FoodSearchRepository
import com.example.noominterview.foodsearch.data.FoodSearchResponse
import com.example.noominterview.util.SchedulersProvider
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class FoodSearchViewModel @Inject constructor(
    private val foodSearchRepository: FoodSearchRepository,
    private val schedulersProvider: SchedulersProvider): ViewModel() {

    companion object {
        private const val MINIMUM_QUERY_LENGTH = 3
    }

    private val _foodSearchLiveData : MutableLiveData<FoodSearchViewState> = MutableLiveData()
    val foodSearchLiveData : LiveData<FoodSearchViewState> = _foodSearchLiveData

    private var pendingRequest: Disposable? = null
    init {
        _foodSearchLiveData.value = TypeThreeCharacters()
    }

    // Assumed that will only be called on main thread from views
    // Can discuss more sophisticated concurrency if needed
    fun searchQueryUpdted(searchQuery: String) {
        if (searchQuery.length < MINIMUM_QUERY_LENGTH) {
            _foodSearchLiveData.value = TypeThreeCharacters()
            return
        }
        pendingRequest?.let {
            it.dispose()
            this.pendingRequest= null
        }
        this.pendingRequest = foodSearchRepository.searchForFood(searchQuery)
            .subscribeOn(schedulersProvider.getIOScheduler())
            .observeOn(schedulersProvider.getMainThreadScheduler())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        _foodSearchLiveData.value = FoodSearchSuccess(body)
                    }
                } else {
                    response.errorBody()?.let { errorResponse ->
                        _foodSearchLiveData.value = FoodSearchError(errorResponse.string())
                    }
                }
            }, {
                _foodSearchLiveData.value = FoodSearchError(it.localizedMessage ?: it.toString())
            })
    }
    override fun onCleared() {
        pendingRequest?.dispose()
        super.onCleared()
    }
}

sealed class FoodSearchViewState
class TypeThreeCharacters : FoodSearchViewState()
data class FoodSearchError(val error: String): FoodSearchViewState()
data class FoodSearchSuccess(val results: List<FoodSearchResponse>): FoodSearchViewState()