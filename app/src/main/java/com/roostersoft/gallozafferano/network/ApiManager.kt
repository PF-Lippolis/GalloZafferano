package com.roostersoft.gallozafferano.network

import com.roostersoft.gallozafferano.service.RecipeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    companion object {
        val BASE_URL = "https://usman-recipes.herokuapp.com/api/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()


        val userService = retrofit.create(RecipeService::class.java)
    }
}