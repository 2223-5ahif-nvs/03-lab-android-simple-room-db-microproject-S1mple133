package at.htl.airport_frontend.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import at.htl.airport_frontend.entity.FavouriteFlight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject


class FlightRepository @Inject constructor(private val flightDao: FlightDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addFavouriteFlight(flight: FavouriteFlight) {
        coroutineScope.launch(Dispatchers.IO) {
            flightDao.insertFlight(flight)
        }
    }

    fun getFavouriteFlights(): LiveData<List<FavouriteFlight>> = flightDao.getAll();
    fun deleteFavouriteFlight(favFlight: FavouriteFlight) {
        coroutineScope.launch(Dispatchers.IO) {
            flightDao.delete(favFlight)
        }
    }

}