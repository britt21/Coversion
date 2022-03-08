package com.example.conversion

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(currencyData: CurrencyData)


    @Query("Select * From CurrencyData")
    fun ReadData(): Flow<List<CurrencyData>>


}