package at.htl.airport_frontend.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_flight")
data class FavouriteFlight(@PrimaryKey(autoGenerate = true) val id: Int,
                           val flightNumber: Int,
                           val departure: String,
                           val arrival: String,
                           val flightType: String,
                           val airportIcao: String
)