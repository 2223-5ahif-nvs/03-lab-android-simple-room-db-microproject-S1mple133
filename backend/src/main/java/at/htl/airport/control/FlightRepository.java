package at.htl.airport.control;

import at.htl.airport.entity.Flight;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FlightRepository implements PanacheRepository<Flight> {
}
