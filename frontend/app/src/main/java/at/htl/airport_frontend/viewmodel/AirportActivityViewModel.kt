package at.htl.airport_frontend.viewmodel;

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.airport_frontend.boundary.FlightAPI
import at.htl.airport_frontend.entity.AirportDto
import at.htl.airport_frontend.entity.FlightDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirportActivityViewModel @Inject constructor(
    private val apiService: FlightAPI): ViewModel()  {

    var errorMessage: String by mutableStateOf("")
    var airportResponse: AirportDto by mutableStateOf(AirportDto("","","",""))

    fun getAirport(icao: String) {
        viewModelScope.launch {
            try {
                val airport = apiService.getAirport(icao)
                airportResponse = airport
            } catch (e: Exception) {
                Log.e("tag", e.stackTraceToString());
                errorMessage = "Could not read data from server!";
            }
        }
    }
}
