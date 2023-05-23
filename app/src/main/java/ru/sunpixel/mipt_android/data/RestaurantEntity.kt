package ru.sunpixel.mipt_android.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "nearest_restaurants")
data class NearestRestaurantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String
)

@Entity(tableName = "popular_restaurants")
data class PopularRestaurantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String
)

fun RemoteRestaurant.mapToNearestRestaurantEntity(): NearestRestaurantEntity =
    NearestRestaurantEntity(id = id, name = name, logo = image, time = deliveryTime)

fun NearestRestaurantEntity.mapToRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(id = id, name = name, image = logo, deliveryTime = time)

fun RemoteRestaurant.mapToPopularRestaurantEntity(): PopularRestaurantEntity =
    PopularRestaurantEntity(id = id, name = name, logo = image, time = deliveryTime)

fun PopularRestaurantEntity.mapToRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(id = id, name = name, image = logo, deliveryTime = time)

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