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
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.airport_frontend.entity.Flight
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import at.htl.airport_frontend.viewmodel.FlightViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val dateTimeFormat: String = "dd.MM.yyyy hh:mm"

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<FlightViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirportfrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MenuList()
                }
            }
        }
    }
}

@Composable
fun MenuList() {
    val mContext = LocalContext.current

    Column() {
        Text(
            modifier = Modifier
                .padding(7.dp, 7.dp, 0.dp, 10.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.MainHeading)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp, 4.dp),
                onClick = {
                    mContext.startActivity(Intent(mContext, FlightListActivity::class.java))
                }) {
                Text(text = stringResource(R.string.flightListButton))
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    AirportfrontendTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MenuList()
        }
    }
}