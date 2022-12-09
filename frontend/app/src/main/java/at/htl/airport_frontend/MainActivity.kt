package at.htl.airport_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.airport_frontend.boundary.FlightAPI
import at.htl.airport_frontend.entity.Flight
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import at.htl.airport_frontend.viewmodel.FlightViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val dateTimeFormat: String = "dd.MM.yyyy hh:mm";

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<FlightViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirportfrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FlightList(mainViewModel.flightListResponse)
                    mainViewModel.getFlightsList();
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AirportfrontendTheme {
        //FlightList()
    }
}

@Composable
fun FlightCard(flight: Flight) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(text = "Type")
                Text(text = "Departure")
                Text(text = "Arrival")
                Text(text = "Airport")
            }

            Column() {
                Text(text = flight.flightType)
                Text(text = formatDateTime(flight.departure))
                Text(text = formatDateTime(flight.arrival))
                Text(text = flight.airportIcao)
            }
        }
    }
}

@Composable
fun FlightList(flights: List<Flight>) {
    LazyRow() {
        items(flights) { flight ->
            FlightCard(flight = flight);
        }
    }
}

fun formatDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat));
}