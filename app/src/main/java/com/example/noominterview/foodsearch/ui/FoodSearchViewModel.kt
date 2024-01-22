package com.example.noominterview.foodsearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noominterview.foodsearch.data.FoodSearchRepository
import com.example.noominterview.foodsearch.data.FoodSearchResponse
import com.example.noominterview.util.SchedulersProvider
import com.example.noominterview.util.SingleLiveEvent
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FoodSearchViewModel @Inject constructor(
    private val foodSearchRepository: FoodSearchRepository,
    private val schedulersProvider: SchedulersProvider): ViewModel() {

    companion object {
        private const val MINIMUM_QUERY_LENGTH = 3
        private const val SEARCH_BAR_DEBOUNCE_MS = 100L
    }

    private val _foodSearchLiveData : MutableLiveData<FoodSearchViewState> = MutableLiveData()
    val foodSearchLiveData : LiveData<FoodSearchViewState> = _foodSearchLiveData

    val foodTappedEvent : SingleLiveEvent<String> = SingleLiveEvent()

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val searchQuerySubject: BehaviorSubject<String> = BehaviorSubject.create()
    init {
        _foodSearchLiveData.value = TypeThreeCharacters()
        searchQuerySubject
            .debounce(SEARCH_BAR_DEBOUNCE_MS, TimeUnit.MILLISECONDS)
            .observeOn(schedulersProvider.getIOScheduler())
            .switchMapSingle { searchQuery ->
                foodSearchRepository.searchForFood(searchQuery)
            }
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
                } },
                {
                    _foodSearchLiveData.value = FoodSearchError(it.localizedMessage ?: it.toString())
                }
            ).addTo(disposable)

    }

    fun searchResultTapped(searchResult: FoodSearchResponse) {
        foodTappedEvent.value = searchResult.name
    }

    // Assumed that will only be called on main thread from views
    // Can discuss more sophisticated concurrency if needed
    fun searchQueryUpdted(searchQuery: String) {
        if (searchQuery.length < MINIMUM_QUERY_LENGTH) {
            _foodSearchLiveData.value = TypeThreeCharacters()
            return
        }
        searchQuerySubject.onNext(searchQuery)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}

sealed class FoodSearchViewState
class TypeThreeCharacters : FoodSearchViewState()
data class FoodSearchError(val error: String): FoodSearchViewState()
data class FoodSearchSuccess(val results: List<FoodSearchResponse>): FoodSearchViewState()