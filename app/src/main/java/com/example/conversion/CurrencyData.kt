package com.example.conversion

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject

@Entity
data class CurrencyData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val currency : String
)

