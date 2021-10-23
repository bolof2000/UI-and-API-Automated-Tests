package apitests.MQ.MQtests.serialization;

import apitests.MQ.apiutils.APIRequests;
import apitests.MQ.apiutils.Common;
import io.restassured.http.ContentType;
import models.User;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UserTests {

    public static String data_source = "/Volumes/Projects/Software Testings/Automations-Tests/src/test/java/apitests/MQ/apiutils/user01.json";

    @Test
    public void getAllUsers(){
        var user = APIRequests.getAllRequests("users").as(User.class);

        //System.out.println(user.getCount());
        Assert.assertEquals(user.getCount(),5);
    }

    @Test
    public void createUser(){

      var  response = given()
                .baseUri(Common.BASE_URL)
                .auth().preemptive().basic(Common.AUTHENTICATION_TOKEN,Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .body(new File(data_source))
                .when()
                .post("users");


        Assert.assertEquals(response.statusCode(),201);


    }

    @Test
    public void getUserWithValidToken(){

       var response = given()
                .baseUri(Common.BASE_URL)
                .auth().preemptive().basic(Common.AUTHENTICATION_TOKEN,Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .pathParam("token","my_user_02")
                .when()
                .get("users/{token}");

        response.prettyPrint();

  Assert.assertEquals(response.getBody().jsonPath().get("token"),"my_user_02");

    }
}
