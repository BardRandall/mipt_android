package ru.sunpixel.mipt_android.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.sunpixel.mipt_android.data.db.RestaurantDao
import ru.sunpixel.mipt_android.data.db.mapToNearestRestaurantEntity
import ru.sunpixel.mipt_android.data.db.mapToPopularRestaurantEntity
import ru.sunpixel.mipt_android.data.db.mapToRemoteRestaurant
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val restaurantDao: RestaurantDao
    ) {

    suspend fun fetchCatalog(): Flow<CatalogResponse> {
        return flow {
            val databaseNearestRest = restaurantDao.getAllNearest()
            val databasePopularRest = restaurantDao.getAllPopular()
            if (databaseNearestRest.isNotEmpty() && databasePopularRest.isNotEmpty()) {
                emit(
                    CatalogResponse(
                        nearest = databaseNearestRest.map { it.mapToRemoteRestaurant() },
                        popular = databasePopularRest.map { it.mapToRemoteRestaurant() },
                        commercial = RemoteCommercial("", "")
                    )
                )
            }

            try {
                val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                    method = HttpMethod.Get
                }.body<CatalogResponse>()

                restaurantDao.insertAllNearest(restaurants = response.nearest.map { it.mapToNearestRestaurantEntity() }
                    .toTypedArray())
                restaurantDao.insertAllPopular(restaurants = response.popular.map { it.mapToPopularRestaurantEntity() }
                    .toTypedArray())
                emit(response)
            } catch (e: Exception) {

            }
        }
    }
}