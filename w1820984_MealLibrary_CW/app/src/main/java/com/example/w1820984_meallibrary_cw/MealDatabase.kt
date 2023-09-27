package com.example.w1820984_meallibrary_cw

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Meal::class], version=2)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}