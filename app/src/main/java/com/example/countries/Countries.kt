package com.example.countries

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.countries.database.AppDatabase

class Countries : Application() {
    private var _database: AppDatabase? = null
    val database get() = requireNotNull(_database)

    override fun onCreate() {
        super.onCreate()
        _database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database.db"
        )
            .allowMainThreadQueries()
            .build()
    }
}

val Context.appDatabase: AppDatabase
    get() = when {
        this is Countries -> database
        else -> applicationContext.appDatabase
    }

