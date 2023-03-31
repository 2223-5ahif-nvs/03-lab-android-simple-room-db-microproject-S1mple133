package at.htl.airport.boundary;

import at.htl.airport.dto.FlightDto;
import at.htl.airport.entity.FlightType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.JsonObject;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class FlightResourceTest {
    @Inject
    ObjectMapper objectMapper;

    @Test
    public void testBasicUsage() throws JsonProcessingException {
        String testIcao = "FLR";
        String testJson = "{\"icao\":\"" + testIcao + "\",\"name\":\"Test Airport\",\"city\":\"Test City\",\"country\":\"Test Country\"}";
        FlightDto testFlight = new FlightDto(312L,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                FlightType.ARRIVAL,
                testIcao);

        // Create Airport
        given()
                .contentType("application/json")
                .body(testJson)
                .when().post("/airport")
                .then()
                .statusCode(200)
                .body(is(testJson));

        // Create Flight
        given()
                .contentType("application/json")
                .body(testFlight)
                .when().post("/flight")
                .then()
                .statusCode(200)
                .body(is(objectMapper.writeValueAsString(testFlight)));

        // List Flight
        given()
                .when().get("/flight")
                .then()
                .statusCode(200)
                .body("flightNumber", hasItem(312));
    }
}