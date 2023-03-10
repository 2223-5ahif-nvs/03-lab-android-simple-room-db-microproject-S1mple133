package at.htl.airport_frontend.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import at.htl.airport_frontend.entity.FavouriteFlight


@Database(entities = [(FavouriteFlight::class)], version = 1, exportSchema = false)
abstract class FlightDatabase : RoomDatabase() {

    abstract fun flightDao(): FlightDao
}