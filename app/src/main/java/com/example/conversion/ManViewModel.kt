package com.example.conversion

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val currencyDao: CurrencyDao) : ViewModel() {

    private val _livecurr = MutableLiveData<NetworkResult<String>>()
    val livecurr : LiveData<NetworkResult<String>>
    get() = _livecurr

    val readData : LiveData<List<CurrencyData>> = currencyDao.ReadData().asLiveData()

   private fun InsertData(currencyData: CurrencyData){
        viewModelScope.launch {
            currencyDao.InsertData(currencyData)
        }
    }

 fun getCurrency(editCurrency : String, fromCurrey : String, toCurrency : String)
    {
        RetrifitApi.RetrofitService.getCurrency(fromCurrey)
            .enqueue(object :
                Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                            Log.d("Response.......", response.body().toString())
                            val result: JsonObject = response.body()!!
                            val rates: JsonObject? = result.getAsJsonObject("conversion_rates")
                            val currte = rates?.get(toCurrency)
                            val fromvalue = rates?.get(fromCurrey)
                            val mutiplier = (editCurrency.toDouble() * fromvalue.toString().toDouble() * currte.toString().toDouble())
                        val data =  ("$editCurrency$fromCurrey is equal to $mutiplier$toCurrency")
                           _livecurr.value = HandelResponse(data)
                        val retrun = _livecurr.value?.data
                        if (retrun!!.isNotEmpty()){
                            offlineCache(retrun)
                        }

                        }
                    }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                   Log.e("Error........", t.message!!)

                }
            })



            }

    private fun offlineCache(rates: String) {
        val currencyData = CurrencyData(0,rates)
        InsertData(currencyData)
    }


    private fun HandelResponse(data: String): NetworkResult<String>? {
         when{
            data.isNotEmpty() ->{
               return NetworkResult.Sucess(data)

            }
            data.isEmpty() ->{
             return   NetworkResult.Error("No result found")
            }
            else ->{
               return NetworkResult.Error("No Internet Connection")
            }
        }
    }
}

