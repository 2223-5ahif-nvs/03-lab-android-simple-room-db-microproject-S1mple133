package at.htl.airport_frontend.entity

import java.time.LocalDateTime

data class Airport (
    var icao: String,
    var name: String,
    var city: String,
    var country: String
    )