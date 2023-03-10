package at.htl.airport_frontend

import android.content.Context
import androidx.room.Room
import at.htl.airport_frontend.persistence.FlightDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FlightDatabase::class.java,
        "flightdb"
    ).build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideYourDao(db: FlightDatabase) =
        db.flightDao()
}