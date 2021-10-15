package com.roostersoft.gallozafferano.repository

import com.roostersoft.gallozafferano.model.RecipeWithIdAndImage

interface RecipeRepository {

    fun fetchRecipes(): List<RecipeWithIdAndImage>
    
}