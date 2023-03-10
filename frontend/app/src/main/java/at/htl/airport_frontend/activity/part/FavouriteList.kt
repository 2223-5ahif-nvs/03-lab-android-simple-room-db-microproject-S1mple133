package at.htl.airport_frontend.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
fun FavouriteFlightCard(flight: FavouriteFlight, onDelete: (FavouriteFlight) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
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

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { onDelete(flight) }) {
                        Text(text = stringResource(id = R.string.delete))
                    }
                }
            }
        }
    )
}

@Composable
fun FavouriteFlightList(flights: List<FavouriteFlight>, onDelete: (FavouriteFlight) -> Unit) {
    val mContext = LocalContext.current

    LazyColumn() {
        items(flights) { flight ->
            FavouriteFlightCard(flight = flight, onDelete)
        }
    }
}