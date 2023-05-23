package ru.sunpixel.mipt_android.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sunpixel.mipt_android.data.RemoteRestaurant
import ru.sunpixel.mipt_android.data.RestaurantRepository
import javax.inject.Inject

sealed class RestaurantEvent {
    object FetchRestaurants: RestaurantEvent()
}

data class RestaurantViewState(
    val nearest: List<RemoteRestaurant> = emptyList(),
    val popular: List<RemoteRestaurant> = emptyList()
)

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val repository: RestaurantRepository): ViewModel() {
    private val _viewState = MutableStateFlow(RestaurantViewState())
    val viewState: StateFlow<RestaurantViewState> = _viewState;

    init {
        viewModelScope.launch {
            fetchRestaurants()
        }
    }


    private suspend fun fetchRestaurants() {
        val response = repository.fetchCatalog()
        _viewState.value = _viewState.value.copy(
            nearest = response.nearest,
            popular = response.popular
        )
    }

}