package at.htl.airport.boundary;

import at.htl.airport.control.AirportRepository;
import at.htl.airport.entity.Airport;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("airport")
public class AirportResource {
    @Inject
    AirportRepository airportRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Airport>> getAllAirports() {
        return airportRepository.listAll();
    }
}
