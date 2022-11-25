package at.htl.airport.boundary;

import at.htl.airport.control.FlightRepository;
import at.htl.airport.dto.FlightDto;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("flight")
public class FlightResource {
    @Inject
    FlightRepository flightRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<FlightDto>> getFlights() {
        return flightRepository.listAll()
                .onItem()
                .transform(
                        flights -> flights.stream()
                                .map(FlightDto::fromFlight)
                                .toList()
                );
    }
}
