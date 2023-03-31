package at.htl.airport.boundary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class AirportResourceTest {
    @Test
    public void testBasicUsage() {
        String testIcao = "LOL";
        String testJson = "{\"icao\":\"" + testIcao + "\",\"name\":\"Test Airport\",\"city\":\"Test City\",\"country\":\"Test Country\"}";
        // Create
        given()
                .contentType("application/json")
                .body(testJson)
                .when().post("/airport")
                .then()
                .statusCode(200)
                .body(is(testJson));

        // List
        given()
                .when().get("/airport")
                .then()
                .statusCode(200)
                .body("icao", hasItem(testIcao));

        // Delete
        given()
                .when().delete("/airport/"+testIcao)
                .then()
                .statusCode(200);

        // List
        given()
                .when().get("/airport")
                .then()
                .statusCode(200)
                .body("icao", everyItem(not(equalTo(testIcao))));
    }
}