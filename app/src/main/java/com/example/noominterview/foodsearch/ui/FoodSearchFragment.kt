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
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FoodSearchFragment: Fragment() {

    companion object {
        private const val SEARCH_BAR_DEBOUNCE_MS = 100L
    }

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
        (requireActivity().application as NoomApplication)
            .getOrCreateFoodSearchComponent()
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

        this.adapter = FoodSearchAdapter()
        binding.foodSearchRecyclerView.adapter = adapter
        binding.foodSearchRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.foodSearchEditText.textChanges()
            .debounce(SEARCH_BAR_DEBOUNCE_MS, TimeUnit.MILLISECONDS)
            .observeOn(schedulersProvider.getMainThreadScheduler())
            .subscribe {
            viewModel.searchQueryUpdted(it.toString())
        }.addTo(disposable)

        observeViewState()
    }

    private fun observeViewState() {
        viewModel.foodSearchLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is FoodSearchError -> {

                }
                is FoodSearchSuccess -> {
                    adapter?.submitList(it.results)
                }
                is TypeThreeCharacters -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        disposable.clear()
        this.adapter = null
        this._binding = null
        super.onDestroyView()
    }
}