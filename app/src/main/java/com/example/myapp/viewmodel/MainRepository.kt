package com.example.myapp.viewmodel

import com.example.myapp.retrofit.RetrofitService

class MainRepository(val retrofitService: RetrofitService) {
    fun getFood() = retrofitService.getFoodRecipe()
}