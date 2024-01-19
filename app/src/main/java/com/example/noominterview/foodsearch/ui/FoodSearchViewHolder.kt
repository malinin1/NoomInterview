package com.example.noominterview.foodsearch.ui

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noominterview.R
import com.example.noominterview.databinding.ItemFoodSearchBinding
import com.example.noominterview.foodsearch.data.FoodSearchResponse

class FoodSearchViewHolder(private val binding: ItemFoodSearchBinding) : ViewHolder(binding.root) {
    fun bind(foodSearchResult: FoodSearchResponse) {
        binding.itemBrandTextView.text = foodSearchResult.brand
        binding.itemNameTextView.text = foodSearchResult.name
        binding.itemPortionTextView.text = itemView.resources.getString(
            R.string.portion_size, foodSearchResult.portion)
        binding.itemCaloriesTextView.text = itemView.resources.getQuantityString(
            R.plurals.calories,
            foodSearchResult.calories,
            foodSearchResult.calories)
    }
}