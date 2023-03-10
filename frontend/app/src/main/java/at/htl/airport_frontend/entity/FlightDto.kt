package at.htl.airport_frontend.entity

data class FlightDto (
    val flightNumber: Number,
    val departure: String,
    val arrival: String,
    val flightType: String,
    val airportIcao: String
    )