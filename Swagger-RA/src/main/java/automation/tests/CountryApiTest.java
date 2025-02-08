package automation.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CountryApiTest {

    @BeforeMethod
    public void setup() {
        // Set base URI for all tests
        RestAssured.baseURI = "https://restcountries.com/v3.1";
    }

    @Test
    public void testGetAllCountries() {
        // Send GET request to /all endpoint
        Response response = given()
                .when()
                .get("/all");

        // Print the response body
        System.out.println("Response Body: " + response.body().prettyPrint());

        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        // Verify response body is not null
        assertNotNull(response.getBody(), "Response body should not be null");

        // Verify response content type
        assertEquals(response.getContentType(), ContentType.JSON.toString(), 
                "Content type should be JSON");
    }
}