package at.htl.airport.boundary;

import at.htl.airport.control.AirportRepository;
import at.htl.airport.control.FlightRepository;
import at.htl.airport.dto.FlightDto;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("flight")
public class FlightResource {
    @Inject
    FlightRepository flightRepository;
    @Inject
    AirportRepository airportRepository;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> createFlight(FlightDto flightDto) {
        return airportRepository.findByIcao(flightDto.airportIcao())
                .onItem()
                .transformToUni(airport -> flightRepository.persistAndFlush(FlightDto.fromFlightDto(flightDto, airport)))
                .onItem()
                .transform(flight -> Response.ok(FlightDto.fromFlight(flight)).build())
                .onFailure()
                .recoverWithItem(() -> Response.notModified().build());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{flightNumber}")
    public Uni<Response> deleteFlight(@PathParam("flightNumber") Long flightNumber) {
        return airportRepository.delete("number", flightNumber)
                .onItem()
                .transform(deleted -> deleted==1 ? Response.ok().build() : Response.notModified().build());
    }
}
