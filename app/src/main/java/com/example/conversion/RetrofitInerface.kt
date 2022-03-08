package com.example.conversion

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

const val  BASE_URL = "https://v6.exchangerate-api.com"
//  https://v6.exchangerate-api.com/v6/056a97308bfbd1b08f9ce93b/latest/USD
interface RetrofitInerface {

    @GET("/v6/056a97308bfbd1b08f9ce93b/latest/{currency}")
    fun getCurrency(@Path("currency") currency: String): Call<JsonObject>

}


val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object RetrifitApi{
    val RetrofitService : RetrofitInerface by lazy {
        retrofit.create(RetrofitInerface::class.java)
    }
}