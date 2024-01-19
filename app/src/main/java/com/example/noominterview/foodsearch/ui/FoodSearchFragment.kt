package com.example.noominterview.foodsearch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noominterview.application.NoomApplication
import com.example.noominterview.databinding.FragmentFoodSearchBinding
import com.example.noominterview.util.ViewModelFactory
import javax.inject.Inject

class FoodSearchFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<FoodSearchViewModel>

    private val viewModel: FoodSearchViewModel by lazy {
        viewModelFactory.get<FoodSearchViewModel>(this)
    }

    private var _binding: FragmentFoodSearchBinding? = null
    private var adapter: FoodSearchAdapter? = null
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
    ): View? {
        this._binding = FragmentFoodSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.adapter = FoodSearchAdapter()
        viewModel.foodSearchLiveData.observe(viewLifecycleOwner) {

        }
    }

    override fun onDestroyView() {
        this.adapter = null
        this._binding = null
        super.onDestroyView()
    }
}