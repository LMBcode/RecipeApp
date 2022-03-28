package com.example.myapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.retrofit.Food
import com.example.myapp.retrofit.Result
import com.example.myapp.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(val mainRepository: MainRepository) : ViewModel(){

    val recipes = MutableLiveData<List<Result>>()
    val errorMessage = MutableLiveData<String>()

    val veganFood = MutableLiveData<List<Result>>()
    val burger = MutableLiveData<List<Result>>()
    val pizza = MutableLiveData<List<Result>>()
    val hot_dog = MutableLiveData<List<Result>>()
    val pasta = MutableLiveData<List<Result>>()

    fun getAllRecipe(){
        val apiInterface = RetrofitService.getInstance().getFoodRecipe()
        apiInterface.enqueue( object : Callback<Food> {
            override fun onResponse(call: Call<Food>?, response: Response<Food>?) {
                val responseBody = response?.body()
                if(responseBody != null)
                    recipes.postValue(responseBody.results)
                    Log.d("TAG","ALL G")
            }
            override fun onFailure(call: Call<Food>?, t: Throwable?) {
                errorMessage.postValue(t?.message)
                Log.e("TAG","FAILURE",t)
            }
        })
    }
    fun getVeganFood(){
        val veganMenu = RetrofitService.getInstance().getVeganMenu()
        veganMenu.enqueue(object : Callback<Food>{
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                val responseBody = response.body()
                if (responseBody != null){
                    veganFood.postValue(responseBody.results)
                    Log.d("TAG1","ALL G for Vegans")
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.e("TAG","FAILURE",t)
                errorMessage.postValue(t.message)
            }

        })
    }
    fun getBurgers(){
        val burgers = RetrofitService.getInstance().getBurgers()
        burgers.enqueue(object : Callback<Food>{
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                val responseBody = response.body()
                if (responseBody != null){
                    burger.postValue(responseBody.results)
                    Log.d("TAG1","ALL W")
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                errorMessage.postValue(t.message)
                Log.e("TAB","FAILURE",t)
            }

        })
    }

    fun getPizza(){
        val burgers = RetrofitService.getInstance().getPizzas()
        burgers.enqueue(object : Callback<Food>{
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                val responseBody = response.body()
                if (responseBody != null){
                    pizza.postValue(responseBody.results)
                    Log.d("TAG1","ALL W")
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                errorMessage.postValue(t.message)
                Log.e("TAB","FAILURE",t)
            }

        })
    }
    fun getHotDog(){
        val hot_dogs = RetrofitService.getInstance().getHotDogs()
        hot_dogs.enqueue(object : Callback<Food>{
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                val responseBody = response.body()
                if(responseBody != null){
                    hot_dog.postValue(responseBody.results)
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
    fun getPasta(){
        val hot_dogs = RetrofitService.getInstance().getPasta()
        hot_dogs.enqueue(object : Callback<Food>{
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                val responseBody = response.body()
                if(responseBody != null){
                    pasta.postValue(responseBody.results)
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
    }