package com.example.countries.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.countries.model.Country

@Database(entities = [Country::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}