package com.example.noominterview.application.dagger

import com.example.noominterview.foodsearch.dagger.FoodSearchComponent
import dagger.Module

@Module(subcomponents = [FoodSearchComponent::class])
interface SubcomponentsModule