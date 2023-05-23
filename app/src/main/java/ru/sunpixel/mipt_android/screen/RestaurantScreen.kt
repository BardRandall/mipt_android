package ru.sunpixel.mipt_android.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.sunpixel.mipt_android.R
import ru.sunpixel.mipt_android.data.RemoteRestaurant
import ru.sunpixel.mipt_android.ui.theme.Mipt_androidTheme

@Composable
fun RestaurantView() {
    val restaurantViewModel: RestaurantViewModel = viewModel()
    val restaurantViewState by restaurantViewModel.viewState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.popular_restaurant),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        RestaurantListView(restaurantViewState.popular)
        Text(
            text = stringResource(R.string.nearest_restaurant),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        RestaurantListView(restaurantViewState.nearest)
    }

}

@Composable
fun RestaurantListView(restaurants: List<RemoteRestaurant>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
    ) {

        items(restaurants) {
                restaurant -> Card(
            modifier = Modifier
                .width(150.dp)
                .height(190.dp)
                .padding(10.dp),
            elevation = 0.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = restaurant.image,
                    contentDescription = restaurant.name,
                    modifier = Modifier.height(70.dp),
                    error = painterResource(
                        id = R.drawable.not_found
                    )
                )

                Text(
                    text = restaurant.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = restaurant.deliveryTime,
                    fontSize = 13.sp
                )
            }

        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantPreview() {
    Mipt_androidTheme {
        RestaurantView()
    }
}