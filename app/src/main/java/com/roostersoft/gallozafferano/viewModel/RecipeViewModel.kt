package com.roostersoft.gallozafferano.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roostersoft.gallozafferano.model.RecipeWithId
import com.roostersoft.gallozafferano.model.RecipeWithIdAndImage
import com.roostersoft.gallozafferano.network.ApiManager
import com.roostersoft.gallozafferano.service.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel: ViewModel() {

    private val TAG = RecipeViewModel::class.java.simpleName

    private val recipeService = ApiManager.recipeService

    private val _recipes = MutableLiveData<List<RecipeWithIdAndImage>>()
    val recipes: LiveData<List<RecipeWithIdAndImage>>
        get() = _recipes
    init {
        fetchRecipes()
    }

    fun fetchRecipes(){

        recipeService.getRecipes().enqueue(object : Callback<List<RecipeWithId>> {
            override fun onResponse(call: Call<List<RecipeWithId>>, response: Response<List<RecipeWithId>>) {
                if(response.isSuccessful){
                    val recipesNoImage = response.body()
                    recipesNoImage?.let {
                        val tempList  = mutableListOf<RecipeWithIdAndImage>()
                        for(recipe in recipesNoImage) {
                            tempList.add(RecipeWithIdAndImage(recipe))
                        }
                        _recipes.postValue(tempList)
                        Log.d(TAG, "SUCCESS")
                    } ?: run {
                        Log.d(TAG, "Request was successful but recipes were null")
                        _recipes.postValue(null)
                    }

                }else{
                    Log.d(TAG, "Error: ${response.code()} body: ${response.errorBody()}")
                    _recipes.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<RecipeWithId>>, t: Throwable) {
                Log.d(TAG, t.localizedMessage ?: "Couldn't retrieve error message")
                _recipes.postValue(null)
            }

        })
    }
}