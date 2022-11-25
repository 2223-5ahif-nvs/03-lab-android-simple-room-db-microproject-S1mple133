package at.htl.airport.control;

import at.htl.airport.entity.Airport;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class AirportRepository implements PanacheRepository<Airport> {
    public Uni<Airport> findByIcao(String airportIcao) {
        return find("icao", airportIcao).firstResult();
    }
}
