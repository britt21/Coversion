package com.example.conversion

import androidx.room.*

@Database(entities = [CurrencyData::class], version = 1)
//@TypeConverters(DataConverter::class)
abstract  class CurrencyDatabase : RoomDatabase() {
    abstract val currencyDao : CurrencyDao


}