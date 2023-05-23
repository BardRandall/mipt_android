package ru.sunpixel.mipt_android.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NearestRestaurantEntity::class, PopularRestaurantEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}