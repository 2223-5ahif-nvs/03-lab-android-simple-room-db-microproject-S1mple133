package at.htl.airport.dto;

import at.htl.airport.entity.Airport;
import at.htl.airport.entity.Flight;
import at.htl.airport.entity.FlightType;

import java.time.LocalDateTime;

public record FlightDto(Long flightNumber,
                        LocalDateTime departure,
                        LocalDateTime arrival,
                        FlightType flightType,
                        String airportIcao)
{
    public static FlightDto fromFlight(Flight flight) {
        return new FlightDto(
                flight.getNumber(),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getFlightType(),
                flight.getAirport().getIcao()
        );
    }

    public static Flight fromFlightDto(FlightDto flightDto, Airport airport) {
        return new Flight(
                flightDto.flightNumber(),
                flightDto.departure(),
                flightDto.arrival(),
                flightDto.flightType(),
                airport
        );
    }
}
