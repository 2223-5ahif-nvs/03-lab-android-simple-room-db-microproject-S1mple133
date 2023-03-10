package at.htl.airport_frontend

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
import at.htl.airport_frontend.entity.FlightDto
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import at.htl.airport_frontend.viewmodel.FlightViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class FlightListActivity : ComponentActivity() {
    private val mainViewModel by viewModels<FlightViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirportfrontendTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FlightList(mainViewModel.flightListResponse) {
                        mainViewModel.addFavouriteFlight(it)
                    }
                    mainViewModel.getFlightsList()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AirportfrontendTheme {
        FlightCard(
            FlightDto(
                airportIcao = "ICAO",
                departure = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                arrival = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                flightNumber = 41,
                flightType = "ARRIVAL"
            ), onClick = {}, openAirportActivity = {}
        )
    }
}

@Composable
fun FlightCard(flight: FlightDto, openAirportActivity: () -> Unit, onClick: (FlightDto) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(flight) } // TODO: add airport activity
            .padding(15.dp),
        content = {
            Row(
                modifier = Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    val icon = if (flight.flightType == "ARRIVAL") R.drawable.arrival
                    else R.drawable.departure

                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "Flight", modifier = Modifier.size(70.dp)
                    )
                }
                Column() {
                    Text(text = stringResource(id = R.string.flight_type))
                    Text(text = stringResource(id = R.string.departure))
                    Text(text = stringResource(id = R.string.arrival))
                    Text(text = stringResource(id = R.string.airport))
                }

                Column() {
                    Text(text = flight.flightType)
                    Text(text = formatDateTime(LocalDateTime.parse(flight.departure)))
                    Text(text = formatDateTime(LocalDateTime.parse(flight.arrival)))
                    Text(text = flight.airportIcao)
                }
            }
        }
    )
}

@Composable
fun FlightList(flights: List<FlightDto>, onClick: (FlightDto) -> Unit) {
    val mContext = LocalContext.current

    LazyColumn() {
        items(flights) { flight ->
            FlightCard(flight = flight,
                onClick = onClick,
                openAirportActivity = {
                    val intent = Intent(mContext, AirportActivity::class.java)
                    intent.putExtra("ICAO", flight.airportIcao)
                    mContext.startActivity(intent)
                })
        }
    }
}

fun formatDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat));
}