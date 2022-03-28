package com.example.myapp.test


import com.google.gson.annotations.SerializedName

data class Nutrition(
    @SerializedName("nutrients")
    val nutrients: List<Nutrient>
)