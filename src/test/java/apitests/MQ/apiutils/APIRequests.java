package apitests.MQ.apiutils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.compress.compressors.pack200.Pack200Utils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class APIRequests {

    public static Response response;

    public  static Response postRequestCallForAnyEndpoint(String endpoint, File file) {

        response = given()
                .auth().basic(Common.AUTHENTICATION_TOKEN, Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .baseUri(Common.BASE_URL)
                .body(file)
                .when()
                .post(endpoint);

        return response;
    }

    public static Response getAllRequests(String endpoint) {
        response = given()
                .auth().basic(Common.AUTHENTICATION_TOKEN, Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .baseUri(Common.BASE_URL)
                .when()
                .get(endpoint);
        return response;
    }

    public static Response getRequestWithToken(String endpoint, String token) {
        response = given()
                .auth().basic(Common.AUTHENTICATION_TOKEN, Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .baseUri(Common.BASE_URL)
                .pathParam("token", token)
                .when()
                .get(endpoint + "/{token}");
        return response;
    }

    public static boolean verifyToken(String token,String endpoint) {

        var response = getAllRequests(endpoint);

        List<Object> data = response.getBody().jsonPath().get("data");
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> user = (Map<String, String>) data.get(i);
           if(user.get("token").equals(token)){
               return true;
           }
        }
        return false;
    }

    public static Response postRequests(String endpoint,String data){

        response = given()
                .auth().basic(Common.AUTHENTICATION_TOKEN, Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .baseUri(Common.BASE_URL)
                .body(data)
                .when()
                .post(endpoint);

        return response;

    }


    public static void main(String[] args){
        System.out.println(verifyToken("user_token_01","users"));
    }
}
