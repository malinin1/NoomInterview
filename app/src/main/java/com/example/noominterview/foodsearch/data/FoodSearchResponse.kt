package com.example.noominterview.foodsearch.data

// In prooduction, split out different data models between data sources and UI layer
data class FoodSearchResponse(val id: Int,
                              val brand: String,
                              val name: String,
                              val calories: Int,
                              val portion: Int)