package at.htl.airport.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Flight extends PanacheEntityBase {
    @Id
    Long number;
    LocalDateTime departure;
    LocalDateTime arrival;
    @Enumerated(EnumType.ORDINAL)
    FlightType flightType;
    @ManyToOne
    Airport airport;

    public Flight(Long number, LocalDateTime departure, LocalDateTime arrival, FlightType flightType, Airport airport) {
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
        this.flightType = flightType;
        this.airport = airport;
    }

    public Flight() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long flightNumber) {
        this.number = flightNumber;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
}
