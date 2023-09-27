package com.example.w1820984_meallibrary_cw

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Query("SELECT * FROM meals")
    fun getAll(): List<Meal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg meals: Meal)
}