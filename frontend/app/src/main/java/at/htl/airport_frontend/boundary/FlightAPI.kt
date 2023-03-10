package at.htl.airport_frontend.boundary

import at.htl.airport_frontend.entity.FlightDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FlightAPI {
    @GET("flight")
    suspend fun getFlights(): List<FlightDto>
}