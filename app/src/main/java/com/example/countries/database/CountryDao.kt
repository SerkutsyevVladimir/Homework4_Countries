package com.example.countries.database

import androidx.room.*
import com.example.countries.model.Country

@Dao
interface CountryDao {
    @Query("SELECT * FROM country")
    fun getAll(): List<Country>

    @Query("SELECT * FROM country WHERE id IN (:countriesIds)")
    fun loadAllByIds(countriesIds: IntArray): List<Country>

    @Query("SELECT * FROM country WHERE country_name LIKE :countryName LIMIT 1")
    fun findByName(countryName: String): Country

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(country: Country)

    @Delete
    fun delete(country: Country)
}