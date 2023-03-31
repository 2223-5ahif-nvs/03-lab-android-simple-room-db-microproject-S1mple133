package at.htl.airport_frontend.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import at.htl.airport_frontend.R
import at.htl.airport_frontend.entity.AirportDto
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import at.htl.airport_frontend.viewmodel.AirportActivityViewModel
import at.htl.airport_frontend.viewmodel.FlightViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class AirportActivity : ComponentActivity() {
    private val mainViewModel by viewModels<AirportActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirportfrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (intent.extras != null && intent.extras!!.getString("ICAO") != null) {
                        AirportPreview(mainViewModel.airportResponse)
                        mainViewModel.getAirport(intent.extras!!.getString("ICAO")!!)
                    } else {
                        UnknownICAO()
                    }
                }
            }
        }
    }
}

@Composable
fun AirportPreview(airport: AirportDto) {
    Column(Modifier
        .fillMaxWidth()) {
        Row(Modifier
            .fillMaxWidth()
            .padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = airport.name)
        }
        AirportCard(airport = airport)
    }
}

@Composable
fun AirportCard(airport: AirportDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(text = stringResource(id = R.string.name))
                        Text(text = stringResource(id = R.string.city))
                        Text(text = stringResource(id = R.string.country))
                        Text(text = stringResource(id = R.string.icao))
                    }

                    Column() {
                        Text(text = airport.name)
                        Text(text = airport.city)
                        Text(text = airport.country)
                        Text(text = airport.icao)
                    }
                }
            }
        }
    )
}

@Composable
fun UnknownICAO() {
    Text(text = "Airport not found!")
}


@Preview(showBackground = true)
@Composable
fun PreviewUnknownICAO() {
    AirportfrontendTheme {
        UnknownICAO()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAirportPreview() {
    AirportfrontendTheme {
        AirportPreview(AirportDto("icao", "name", "city", "country"))
    }
}