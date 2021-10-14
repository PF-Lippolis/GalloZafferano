package com.roostersoft.gallozafferano

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roostersoft.gallozafferano.model.RecipeWithId
import com.roostersoft.gallozafferano.model.RecipeWithIdAndImage

class RecipeViewModel: ViewModel() {

    private val _recipes = MutableLiveData<List<RecipeWithIdAndImage>>()
    val recipes: LiveData<List<RecipeWithIdAndImage>>
        get() = _recipes
    init {
        _recipes.postValue(listOf(
            RecipeWithIdAndImage(RecipeWithId("0000", "Pollo", "Pollo con patate")),
            RecipeWithIdAndImage(RecipeWithId("0001", "Torta", "Torta ai lamponi"))
        ))
    }

}