package com.example.noominterview.foodsearch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noominterview.application.NoomApplication
import com.example.noominterview.databinding.FragmentFoodSearchBinding
import com.example.noominterview.util.SchedulersProvider
import com.example.noominterview.util.ViewModelFactory
import com.example.noominterview.util.hide
import com.example.noominterview.util.show
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class FoodSearchFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<FoodSearchViewModel>

    @Inject
    lateinit var schedulersProvider: SchedulersProvider

    private val viewModel: FoodSearchViewModel by lazy {
        viewModelFactory.get<FoodSearchViewModel>(this)
    }

    private var _binding: FragmentFoodSearchBinding? = null
    private var adapter: FoodSearchAdapter? = null
    private val disposable = CompositeDisposable()
    private val binding: FragmentFoodSearchBinding get() {
        return _binding!!
    }
    override fun onAttach(context: Context) {
        NoomApplication.getFoodSearchComponent()
            .inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentFoodSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.adapter = FoodSearchAdapter({ foodSearchResponse ->
            viewModel.searchResultTapped(foodSearchResponse)
        })
        binding.foodSearchRecyclerView.adapter = adapter
        binding.foodSearchRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.foodSearchEditText.textChanges()
            .subscribe {
            viewModel.searchQueryUpdated(it.toString())
        }.addTo(disposable)

        observeViewState()
    }

    private fun observeViewState() {
        viewModel.foodSearchLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is FoodSearchError -> {
                    // Display something for error state
                }
                is FoodSearchSuccess -> {
                    adapter?.submitList(it.results)
                    binding.foodSearchHint.hide()
                }
                is TypeThreeCharacters -> {
                    binding.foodSearchHint.show()
                }
            }
        }

        viewModel.foodTappedEvent.observe(viewLifecycleOwner) {
            showFoodNameSnackbar(it)
        }
    }

    private fun showFoodNameSnackbar(foodName: String) {
        Snackbar.make(binding.root, foodName, Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun onDestroyView() {
        disposable.clear()
        this.adapter = null
        this._binding = null
        super.onDestroyView()
    }
}