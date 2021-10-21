package apitests.MQ.MQtests;

import apitests.MQ.apiutils.Common;
import apitests.MQ.apiutils.APIRequests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CardProduct {

    Response response;
    SoftAssert softAssert = new SoftAssert();

    public static String data_source = "/Volumes/Projects/Software Testings/Automations-Tests/src/test/java/apitests/MQ/card_product.json";

    APIRequests apiRequests = new APIRequests();

    @Test
    public void createCardProduct() {

        response = given()
                .baseUri(Common.BASE_URL)
                .auth().preemptive().basic(Common.AUTHENTICATION_TOKEN, Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .body(new File("/Volumes/Projects/Software Testings/Automations-Tests/src/test/java/apitests/MQ/card_product.json"))
                .when()
                .post("cardproducts");

        System.out.println(response.statusCode());
        response.prettyPrint();

        softAssert.assertEquals(response.statusCode(), 201);

        softAssert.assertAll();
    }

    @Test
    public void getCardProductWithToken(){

        response = given()
                .baseUri(Common.BASE_URL)
                .auth().preemptive().basic(Common.AUTHENTICATION_TOKEN, Common.ADMIN_ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .pathParam("token","ATM_card_01")
                .when()
                .get("cardproducts/{token}");

        System.out.println(response.statusCode());
        response.prettyPrint();

        softAssert.assertEquals(response.statusCode(), 200);

        softAssert.assertAll();
    }

    @Test
    public void createCardProductWithJsonFile(){
        var response = apiRequests.postRequestCallForAnyEndpoint("cardproducts",new File(data_source));
        System.out.println(response.statusCode());
        response.prettyPrint();

    }

    @Test
    public void getAllCardProducts(){

        var response = APIRequests.getAllRequests("cardproducts");

        response.prettyPrint();
        System.out.println(response.statusCode());
    }



}
