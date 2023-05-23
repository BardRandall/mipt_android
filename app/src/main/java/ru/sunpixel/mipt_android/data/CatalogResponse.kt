package ru.sunpixel.mipt_android.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatalogResponse(
    @SerialName("nearest") val nearest: List<RemoteRestaurant>,
    @SerialName("popular") val popular: List<RemoteRestaurant>,
    @SerialName("commercial") val commercial: RemoteCommercial
)

@Serializable
data class RemoteRestaurant(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("deliveryTime") val deliveryTime: String,
    @SerialName("image") val image: String
)

@Serializable
data class RemoteCommercial(
    @SerialName("picture") val picture: String,
    @SerialName("url") val url: String
)