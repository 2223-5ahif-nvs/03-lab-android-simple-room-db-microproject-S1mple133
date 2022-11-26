package at.htl.airport_frontend.entity

import java.time.LocalDateTime

data class Flight (
    var flightNumber: Number,
    var departure: LocalDateTime,
    var arrival: LocalDateTime,
    var flightType: String,
    var airportIcao: String
    )