package at.htl.airport_frontend.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.airport_frontend.R
import at.htl.airport_frontend.entity.FlightDto
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import at.htl.airport_frontend.viewmodel.FlightViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
            .padding(15.dp),
        content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                        Text(text = stringResource(id = R.string.departure))
                        Text(text = stringResource(id = R.string.arrival))
                        Text(text = stringResource(id = R.string.airport))
                        Text(text = stringResource(id = R.string.flight_number))
                    }

                    Column() {
                        Text(text = formatDateTime(LocalDateTime.parse(flight.departure)))
                        Text(text = formatDateTime(LocalDateTime.parse(flight.arrival)))
                        Text(text = flight.airportIcao)
                        Text(text = "" + flight.flightNumber)
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {onClick(flight)}) {
                        Text(text = stringResource(id = R.string.favorite))
                    }
                    Button(onClick = openAirportActivity) {
                        Text(text = stringResource(id = R.string.airportDetails))
                    }
                }
            }
        }
    )
}

@Composable
fun FlightList(flights: List<FlightDto>, onClick: (FlightDto) -> Unit, icao: String, onlyDepartures: Boolean) {
    val mContext = LocalContext.current
    val openDialog = remember { mutableStateOf(false)  }

    if (openDialog.value) {

        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = stringResource(id = R.string.success))
            },
            text = {
                Text(text = stringResource(id = R.string.succesfullyMarked))
            },
            confirmButton = {
            },
            dismissButton = {
                Button(

                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(text = stringResource(id = R.string.dismiss))
                }
            }
        )
    }

    LazyColumn() {
        items(flights) { flight ->
            if((icao.isBlank() || flight.airportIcao.lowercase().contains(icao.lowercase())) &&
                (!onlyDepartures || flight.flightType == "DEPARTURE")) {
                FlightCard(flight = flight,
                    onClick = {
                        onClick(it)
                        openDialog.value = true
                    },
                    openAirportActivity = {
                        val intent = Intent(mContext, AirportActivity::class.java)
                        intent.putExtra("ICAO", flight.airportIcao)
                        mContext.startActivity(intent)
                    })
            }
        }
    }
}

@Composable
fun FilteredFlightList(flights: List<FlightDto>, onClick: (FlightDto) -> Unit) {
    var icao = remember { mutableStateOf("")  }
    val onlyDepartures = remember { mutableStateOf(true) }

    FlightAirportFilter(newIcao = {
        icao.value = it.lowercase()
    }) {
        onlyDepartures.value = it;
    }


    FlightList(flights, onClick, icao.value, onlyDepartures.value)
}

@Composable
fun FlightAirportFilter(newIcao: (String) -> Unit, checkedChange: (Boolean) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val checkedState = remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp,horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start) {
        TextField(
            value = text,
            onValueChange = {
                newIcao(it.text)
                text = it
            },
            label = { Text(text = "Airport ICAO Filter ") },
            placeholder = { Text(text = "SEK") },
        )

        Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it; checkedChange(it)}
            )

            Text(text = stringResource(id = R.string.checkBoxDeptOnly))
        }
    }
}

fun formatDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat));
}