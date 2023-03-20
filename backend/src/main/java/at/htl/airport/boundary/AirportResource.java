package at.htl.airport.boundary;

import at.htl.airport.control.AirportRepository;
import at.htl.airport.entity.Airport;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @GET
    @Path("/{icao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Airport> getByICAO(@PathParam("icao") String icao) {
        return airportRepository.findByIcao(icao);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Uni<Response> createAirport(Airport airport) {
        return airportRepository.persistAndFlush(airport)
                .onItem()
                .transform(airport1 -> Response.ok(airport1).build())
                .onFailure()
                .recoverWithItem(() -> Response.notModified().build());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{airportIcao}")
    public Uni<Response> deleteFlight(@PathParam("airportIcao") String icao) {
        return airportRepository.delete("icao", icao)
                .onItem()
                .transform(deleted -> deleted==1 ? Response.ok().build() : Response.notModified().build());
    }
}
