package apitests.MQ.MQtests;

import apitests.MQ.apiutils.Common;
import apitests.MQ.apiutils.APIRequests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class Accountholder {

    Response response;
    SoftAssert softAssert = new SoftAssert();


    public static String data_source = "/Volumes/Projects/Software Testings/Automations-Tests/src/test/java/apitests/MQ/apiutils/user01.json";

    APIRequests postRequest = new APIRequests();

    @Test
    public void createUser(){

        response = given()
                .baseUri(Common.BASE_URL)
                .auth().preemptive().basic(Common.AUTHENTICATION_TOKEN,Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .body(new File(data_source))
                .when()
                .post("users");


        softAssert.assertEquals(response.statusCode(),201);
        response.prettyPrint();

    }

    @Test
    public void createUserWithExternalJsonFile(){
        Response response  = postRequest.postRequestCallForAnyEndpoint("users",new File(data_source));

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),201);
    }

    @Test
    public void getUserWithValidToken(){

        response = given()
                .baseUri(Common.BASE_URL)
                .auth().preemptive().basic(Common.AUTHENTICATION_TOKEN,Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .pathParam("token","my_user_02")
                .when()
                .get("users/{token}");

        response.prettyPrint();

        softAssert.assertEquals(response.getBody().jsonPath().get("token"),"my_user_02");
        softAssert.assertEquals(200,response.statusCode());
        softAssert.assertAll();
    }

    @Test
    public void getAllUsers(){
        response = given()
                .baseUri(Common.BASE_URL)
                .auth().preemptive().basic(Common.AUTHENTICATION_TOKEN,Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("users");

        response.prettyPrint();

       // softAssert.assertEquals(response.getBody().jsonPath().get("token"),"my_user_02");
        softAssert.assertEquals(200,response.statusCode());
        softAssert.assertAll();
    }

    @Test
    public void updateUser(){
        response = given()
                .baseUri(Common.BASE_URL)
                .auth().preemptive().basic(Common.AUTHENTICATION_TOKEN,Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .pathParam("token","my_user_02")
                .body(new File("/Volumes/Projects/Software Testings/Automations-Tests/src/test/java/apitests/MQ/updateuser.json"))
                .when()
                .put("users/{token}");

        response.prettyPrint();

        // softAssert.assertEquals(response.getBody().jsonPath().get("token"),"my_user_02");
        softAssert.assertEquals(200,response.statusCode());
        softAssert.assertAll();

    }

}
