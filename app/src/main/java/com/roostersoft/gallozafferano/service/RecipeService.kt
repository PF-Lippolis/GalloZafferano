package com.roostersoft.gallozafferano.service

import com.roostersoft.gallozafferano.model.Recipe
import com.roostersoft.gallozafferano.model.RecipeWithId
import retrofit2.Call
import retrofit2.http.*

interface RecipeService {

    @GET("recipes")
    fun getRecipes(): Call<List<RecipeWithId>>

    @POST("recipes")
    fun postRecipe(@Body recipe: Recipe): Call<RecipeWithId>

    @DELETE("recipes/{id}")
    fun deleteRecipe(@Path("id") id: String): Call<RecipeWithId>
}