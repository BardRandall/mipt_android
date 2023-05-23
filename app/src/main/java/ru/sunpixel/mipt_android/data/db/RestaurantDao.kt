package ru.sunpixel.mipt_android.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM nearest_restaurants")
    suspend fun getAllNearest(): List<NearestRestaurantEntity>

    @Query("SELECT * FROM popular_restaurants")
    suspend fun getAllPopular(): List<PopularRestaurantEntity>

    @Insert
    suspend fun insertAllNearest(vararg restaurants: NearestRestaurantEntity)

    @Insert
    suspend fun insertAllPopular(vararg restaurants: PopularRestaurantEntity)

    @Delete
    suspend fun delete(restaurantEntity: NearestRestaurantEntity)
}