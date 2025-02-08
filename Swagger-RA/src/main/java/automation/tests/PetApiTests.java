package automation.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PetApiTests {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testUpdateExistingPet() {
        // Update an existing pet
        String petId = "1";
        String requestBody = "{\"id\": " + petId + ", \"category\": {\"id\": 1, \"name\": \"Dogs\"}, " +
                "\"name\": \"Buddy\", \"photoUrls\": [\"photoUrl\"], " +
                "\"tags\": [{\"id\": 1, \"name\": \"Tag\"}], \"status\": \"available\"}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/pet")
                .then()
                .statusCode(200);

        System.out.println("Response after updating pet: ");
    }

    @Test
    public void testAddNewPet() {
        // Add a new pet
        String requestBody = "{\"id\": 999, \"category\": {\"id\": 1, \"name\": \"Dogs\"}, " +
                "\"name\": \"Max\", \"photoUrls\": [\"photoUrl\"], " +
                "\"tags\": [{\"id\": 1, \"name\": \"Tag\"}], \"status\": \"available\"}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .statusCode(200);

        System.out.println("Response after adding new pet: ");
    }

    @Test
    public void testFindPetsByStatus() {
        // Find pets by status
        String status = "available";

        given()
                .when()
                .queryParam("status", status)
                .get("/pet/findByStatus")
                .then()
                .statusCode(200);

        System.out.println("Response for pets with status '" + status + "': ");
    }
}