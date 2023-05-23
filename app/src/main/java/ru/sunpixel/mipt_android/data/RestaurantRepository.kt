package ru.sunpixel.mipt_android.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val httpClient: HttpClient) {

    suspend fun fetchCatalog(): CatalogResponse {
        val response = httpClient.request("http://195.2.84.138:8081/catalog") {
            method = HttpMethod.Get
        }.body<CatalogResponse>()

        return response
    }
}