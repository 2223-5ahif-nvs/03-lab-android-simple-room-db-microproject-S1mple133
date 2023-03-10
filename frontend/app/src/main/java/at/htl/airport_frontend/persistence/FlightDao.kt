package at.htl.airport_frontend.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import at.htl.airport_frontend.entity.FavouriteFlight
import dagger.Provides
import java.util.concurrent.Flow

@Dao
interface FlightDao {
    @Query("select * from favourite_flight")
    fun getAll(): LiveData<List<FavouriteFlight>>


    @Insert
    fun insertFlight(vararg favouriteFlights: FavouriteFlight)
}