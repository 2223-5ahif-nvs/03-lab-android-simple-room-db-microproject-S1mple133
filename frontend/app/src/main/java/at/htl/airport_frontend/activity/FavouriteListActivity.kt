package at.htl.airport_frontend.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.airport_frontend.entity.FavouriteFlight
import at.htl.airport_frontend.entity.FlightDto
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import at.htl.airport_frontend.viewmodel.FlightViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class FavouriteListActivity : ComponentActivity() {
    private val mainViewModel by viewModels<FlightViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirportfrontendTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FavouriteFlightList(mainViewModel.favouriteFlightResponse)
                }
            }
        }
    }
}

@Composable
fun FavouriteFlightCard(flight: FavouriteFlight) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        content = {
            Row(
                modifier = Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(text = stringResource(id = R.string.flight_number))
                }

                Column() {
                    Text(text = "" + flight.flightNumber)
                }
            }
        }
    )
}

@Composable
fun FavouriteFlightList(flights: List<FavouriteFlight>) {
    val mContext = LocalContext.current

    LazyColumn() {
        items(flights) { flight ->
            FavouriteFlightCard(flight = flight)
        }
    }
}