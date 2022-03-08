package com.example.conversion

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types



 var gson = Gson()


class DataConverter {


    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodList: CurrencyData): String{
        return gson.toJson(foodList)
    }

    @TypeConverter
    fun stringToJson(data: String): CurrencyData{
        val listType = object : TypeToken<CurrencyData>(){}.type
        return gson.fromJson(data, listType)
    }




}