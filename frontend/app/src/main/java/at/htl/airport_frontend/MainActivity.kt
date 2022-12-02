package at.htl.airport_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.htl.airport_frontend.entity.Flight
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirportfrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FlightList()
                }
            }
        }
    }
}

@Composable
fun FlightList() {
    Text(text = "Hello!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AirportfrontendTheme {
        FlightCard(Flight(airportIcao = "ICAO",
            departure = LocalDateTime.now(),
            arrival = LocalDateTime.now().plusDays(1),
            flightNumber = 41,
            flightType = "ARRIVAL"
        ));
    }
}

@Composable
fun FlightCard(flight: Flight) {
    Column() {
        Row() {
            Text(text = "Type: " + flight.flightType)
        }

        Row() {
            Text(text = "Departure: " + flight.departure)
        }

        Row() {
            Text(text = "Type: " + flight.flightType)
        }

        Row() {
            Text(text = "Type: " + flight.flightType)
        }
    }
}