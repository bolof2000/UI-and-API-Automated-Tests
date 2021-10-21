package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.data.DataCreation;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class APIRequests {

    public static String userSignUp() throws IOException {
        Response response;
        response = given()
                .contentType(ContentType.JSON)
                .baseUri("https://api.realworld.io/api")
                .body(DataCreation.dataGenSignup())
                .when()
                .post("/users");

        return response.getBody().jsonPath().getString("user.token");

    }
}
