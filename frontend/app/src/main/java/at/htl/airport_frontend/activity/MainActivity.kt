package at.htl.airport_frontend.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.airport_frontend.R
import at.htl.airport_frontend.ui.theme.AirportfrontendTheme
import at.htl.airport_frontend.viewmodel.FlightViewModel
import dagger.hilt.android.AndroidEntryPoint

const val dateTimeFormat: String = "dd.MM.yyyy hh:mm"

@AndroidEntryPoint
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
                    TabScreen(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun TabScreen(viewModel: FlightViewModel) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Flights", "Favourites")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            1 -> FavouriteFlightList(viewModel.favouriteFlightResponse)
            0 -> {
                FlightList(viewModel.flightListResponse) {
                    viewModel.addFavouriteFlight(it)
                }

                viewModel.getFlightsList()
            }
        }
    }
}

/*@Composable
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
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp, 4.dp),
                onClick = {
                    mContext.startActivity(Intent(mContext, FavouriteListActivity::class.java))
                }) {
                Text(text = stringResource(R.string.favouriteListButton))
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
}*/