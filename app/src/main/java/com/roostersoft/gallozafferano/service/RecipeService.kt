package com.roostersoft.gallozafferano.service

import com.roostersoft.gallozafferano.model.RecipeWithId
import retrofit2.Call
import retrofit2.http.GET

interface RecipeService {

    @GET("recipes")
    fun getRecipes(): Call<List<RecipeWithId>>
}