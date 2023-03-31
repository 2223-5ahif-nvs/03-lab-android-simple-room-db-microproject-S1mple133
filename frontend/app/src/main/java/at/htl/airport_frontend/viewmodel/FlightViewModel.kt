package at.htl.airport_frontend.viewmodel

import android.util.Log
import androidx.annotation.Nullable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.airport_frontend.boundary.FlightAPI
import at.htl.airport_frontend.entity.FavouriteFlight
import at.htl.airport_frontend.entity.FlightDto
import at.htl.airport_frontend.persistence.FlightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightViewModel @Inject constructor(
    private val flightRepository: FlightRepository,
    private val apiService: FlightAPI
) : ViewModel() {
    var flightListResponse: List<FlightDto> by mutableStateOf(listOf())
    var favouriteFlightResponse: List<FavouriteFlight> by mutableStateOf(listOf())

    init {
        flightRepository.getFavouriteFlights().observeForever { sections ->
            if (sections == null || sections.isEmpty()) {
                // No data in your database, call your api for data
            } else {
                favouriteFlightResponse = favouriteFlightResponse + sections;
            }
        }
    }

    var errorMessage: String by mutableStateOf("")
    fun getFlightsList() {
        viewModelScope.launch {
            try {
                val flightList = apiService.getFlights()
                flightListResponse = flightList
            } catch (e: Exception) {
                Log.e("tag", e.stackTraceToString());
                errorMessage = "Could not read data from server!";
            }
        }
    }

    fun addFavouriteFlight(flight: FlightDto) {
        flightRepository.addFavouriteFlight(
            FavouriteFlight(
                0,
                flight.flightNumber.toInt(),
                airportIcao = flight.airportIcao,
                arrival = flight.arrival,
                flightType = flight.flightType,
                departure = flight.departure
            )
        );
        favouriteFlightResponse = listOf()
        flightRepository.getFavouriteFlights()
    }

    fun deleteFavouriteFlight(it: FavouriteFlight) {
        flightRepository.deleteFavouriteFlight(it);
        favouriteFlightResponse = listOf()
        flightRepository.getFavouriteFlights()
    }
}