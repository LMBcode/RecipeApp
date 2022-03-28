package com.example.myapp.retrofit


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("imageType")
    val imageType: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("pricePerServing")
    val price : Double,
    @SerializedName("readyInMinutes")
    val readyIn : Int,
    @SerializedName("Calories")
    val calories : Int
)