package com.example.myapp.retrofit

import android.graphics.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("recipes/complexSearch")
    fun getFoodRecipe(
        @Query("apiKey") apiKey : String ="ad325fd6f08f41628854d747c4a8b66f",
        @Query("maxCalories") maxCalories : Int = 150,
        @Query("addRecipeInformation") addRecipeInformation : Boolean = true
    ) : Call<Food>

    @GET("recipes/complexSearch")
    fun getVeganMenu(
        @Query("apiKey") apiKey : String ="ad325fd6f08f41628854d747c4a8b66f",
        @Query("diet") diet : String = "vegan",
        @Query("addRecipeInformation") addRecipeInformation : Boolean = true
    ) : Call<Food>

    @GET("recipes/complexSearch")
    fun getBurgers(
        @Query("apiKey") apiKey: String = "ad325fd6f08f41628854d747c4a8b66f",
        @Query("query") query : String = "burger",
        @Query("number") number : Int = 50
    ) : Call<Food>

    @GET("recipes/complexSearch")
    fun getPizzas(
        @Query("apiKey") apiKey: String = "ad325fd6f08f41628854d747c4a8b66f",
        @Query("query") query : String = "pizza",
        @Query("number") number : Int = 50
    ) : Call<Food>

    @GET("recipes/complexSearch")
    fun getHotDogs(
        @Query("apiKey") apiKey: String = "ad325fd6f08f41628854d747c4a8b66f",
        @Query("query") query : String = "hotdog",
        @Query("number") number : Int = 50
    ) : Call<Food>

    @GET("recipes/complexSearch")
    fun getPasta(
        @Query("apiKey") apiKey: String = "ad325fd6f08f41628854d747c4a8b66f",
        @Query("query") query : String = "pasta",
        @Query("number") number : Int = 50
    ) : Call<Food>
    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.spoonacular.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}