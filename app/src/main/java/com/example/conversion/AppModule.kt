package com.example.conversion

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): CurrencyDatabase{
        return  Room.databaseBuilder(context, CurrencyDatabase::class.java, "curecy_database").build()
    }

    @Singleton
    @Provides
    fun getDao(currencyDatabase: CurrencyDatabase): CurrencyDao{
        return currencyDatabase.currencyDao
    }

}