package at.htl.airport_frontend.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.airport_frontend.boundary.FlightAPI
import at.htl.airport_frontend.entity.Flight
import kotlinx.coroutines.launch

class FlightViewModel : ViewModel() {
    var flightListResponse:List<Flight> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getFlightsList() {
        viewModelScope.launch {
            val apiService = FlightAPI.getInstance()
            try {
                val movieList = apiService.getFlights()
                flightListResponse = movieList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}