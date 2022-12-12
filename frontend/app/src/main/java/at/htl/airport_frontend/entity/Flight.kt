package at.htl.airport_frontend.entity

import java.time.LocalDateTime

data class Flight (
    val flightNumber: Number,
    val departure: String,
    val arrival: String,
    val flightType: String,
    val airportIcao: String
    )