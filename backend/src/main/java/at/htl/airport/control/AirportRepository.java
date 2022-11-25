package at.htl.airport.control;

import at.htl.airport.entity.Airport;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AirportRepository implements PanacheRepository<Airport> {
}
