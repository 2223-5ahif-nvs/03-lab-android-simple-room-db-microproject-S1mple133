package at.htl.airport_frontend.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import at.htl.airport_frontend.entity.FavouriteFlight
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import at.htl.airport_frontend.viewmodel.FlightViewModel
import dagger.hilt.android.AndroidEntryPoint
import at.htl.airport_frontend.R

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