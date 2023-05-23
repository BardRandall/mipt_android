package ru.sunpixel.mipt_android.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sunpixel.mipt_android.data.RemoteRestaurant

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