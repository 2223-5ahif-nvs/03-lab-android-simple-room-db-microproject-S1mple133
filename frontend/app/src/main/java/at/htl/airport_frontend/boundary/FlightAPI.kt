package at.htl.airport_frontend.boundary

import at.htl.airport_frontend.entity.AirportDto
import at.htl.airport_frontend.entity.FlightDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FlightAPI {
    @GET("flight")
    suspend fun getFlights(): List<FlightDto>

    @GET("airport/{icao}")
    suspend fun getAirport(@Path("icao") icao: String): AirportDto
}