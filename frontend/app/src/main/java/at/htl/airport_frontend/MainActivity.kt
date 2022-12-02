package at.htl.airport_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import at.htl.airport_frontend.entity.Flight
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val dateTimeFormat: String = "dd.MM.yyyy hh:mm";

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
        FlightCard(
            Flight(
                airportIcao = "ICAO",
                departure = LocalDateTime.now(),
                arrival = LocalDateTime.now().plusDays(1),
                flightNumber = 41,
                flightType = "ARRIVAL"
            )
        );
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

fun formatDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat));
}
