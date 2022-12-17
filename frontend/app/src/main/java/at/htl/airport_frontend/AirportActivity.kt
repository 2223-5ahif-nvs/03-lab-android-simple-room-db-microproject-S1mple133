package at.htl.airport_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme

class AirportActivity : ComponentActivity() {
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
                        AirportCard(icao = intent.extras!!.getString("ICAO")!!)
                    }
                    else {
                        UnknownICAO()
                    }
                }
            }
        }
    }
}

@Composable
fun AirportCard(icao: String) {
    Text(text = "Hello $icao!")
}

@Composable
fun UnknownICAO() {
    Text(text = "ICAO not found!")
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
fun PreviewAirportCard() {
    AirportfrontendTheme {
        AirportCard("ASDF")
    }
}