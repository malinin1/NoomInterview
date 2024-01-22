package com.example.noominterview.foodsearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.noominterview.databinding.ItemFoodSearchBinding
import com.example.noominterview.foodsearch.data.FoodSearchResponse

class FoodSearchAdapter(private val listener: (FoodSearchResponse) -> Unit):
    ListAdapter<FoodSearchResponse, FoodSearchViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object :  DiffUtil.ItemCallback<FoodSearchResponse>() {

            override fun areItemsTheSame(
                oldItem: FoodSearchResponse,
                newItem: FoodSearchResponse
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FoodSearchResponse,
                newItem: FoodSearchResponse
            ): Boolean {
                return oldItem.id == newItem.id && oldItem.brand == newItem.brand
                        && oldItem.name == newItem.name && oldItem.calories == newItem.calories
            }
        }
    }

    init {
        setHasStableIds(true)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodSearchBinding.inflate(inflater, parent, false)
        return FoodSearchViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FoodSearchViewHolder, position: Int) {
       holder.bind(getItem(position), listener)
    }
}