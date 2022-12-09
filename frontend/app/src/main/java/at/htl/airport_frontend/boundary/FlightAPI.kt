package at.htl.airport_frontend.boundary

import at.htl.airport_frontend.entity.Flight
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FlightAPI {
    @GET("flight")
    fun getFlights(): List<Flight>

    companion object {
        var apiService: FlightAPI? = null
        fun getInstance() : FlightAPI {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://student.cloud.htl-leonding.ac.at/d.pavelescu/airport-backend/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(FlightAPI::class.java)
            }
            return apiService!!
        }
    }
}