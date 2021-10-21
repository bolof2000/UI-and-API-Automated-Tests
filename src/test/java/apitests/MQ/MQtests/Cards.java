package apitests.MQ.MQtests;

import apitests.MQ.apiutils.APIRequests;
import io.restassured.response.Response;
import jodd.json.meta.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cards {


    APIRequests apiRequest = new APIRequests();
    public static String data_source = "/Volumes/Projects/Software Testings/Automations-Tests/src/test/java/apitests/MQ/apiutils/createCard.jon";


    @Test
    public void createCardWithValidCardProductAndValidUserToken() {

        var user_api_response = APIRequests.getAllRequests("users");
        var card_product_response = APIRequests.getAllRequests("cardproducts");

        var userToken = APIRequests.verifyToken("user_token_02","users");
        System.out.println(userToken);

        var cardProductToken = APIRequests.verifyToken("ATM_card_02","cardproducts");
        System.out.println(cardProductToken);
        try {
            if (userToken && cardProductToken) {
                var cardResponse = apiRequest.postRequestCallForAnyEndpoint("cards", new File(data_source));

                System.out.println(cardResponse.statusCode());
                cardResponse.prettyPrint();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getAll(){

        var response = APIRequests.getAllRequests("users");
        var cardProductsResponse = APIRequests.getAllRequests("cardproducts");

       // response.prettyPrint();
        List<Object> tokenData = response.getBody().jsonPath().get("data");
        for(int i=0;i<tokenData.size();i++){
            Map<String,String> users = (Map<String, String>) tokenData.get(i);
           // System.out.println(users.get("token"));
        }

        cardProductsResponse.prettyPrint();
        List<Object> tokenCardProducts = response.getBody().jsonPath().get("data");
        for(int i=0;i<tokenCardProducts.size();i++){
            Map<String,String> users = (Map<String, String>) tokenCardProducts.get(i);
           // System.out.println(users.get("token"));
        }
    }

    @Test
    public void getAllCards(){

        var response = APIRequests.getAllRequests("cardproducts");
        response.prettyPrint();
        System.out.println(response.statusCode());
    }


    @Test
    public void createCard(){

        //todo create user
        String user_data = "{\n" +
                "  \"first_name\": \"Bbsegungtetsts\",\n" +
                "  \"last_name\": \"JSONBOLOFINDE01\",\n" +
                "  \"token\": \"User_MQ_06\",\n" +
                "  \"email\": \"MQ01usertoken6@gmail.com\",\n" +
                "  \"password\": \"P@ssw0rd\"\n" +
                "}";

        var user_response = APIRequests.postRequests("users",user_data);

        String user_token = user_response.getBody().jsonPath().get("token").toString();


        //todo create card products
        String card_product_data = "{\n" +
                "  \"name\": \"MQ_PHYSICAL_CARD_05\",\n" +
                "  \"start_date\": \"2024-01-02\"\n" +
                "}";


        var card_product_response = APIRequests.postRequests("cardproducts",card_product_data);

        String card_product_token = card_product_response.getBody().jsonPath().get("token").toString();


        //todo create cards
        JSONObject card_data_json = new JSONObject();
        card_data_json.put("card_product_token",card_product_token);
        card_data_json.put("user_token",user_token);

      var card_response = APIRequests.postRequests("cards",card_data_json.toJSONString());

        System.out.println(card_response.statusCode());
      card_response.prettyPrint();


    }

    @Test
    public void createUser(){
        String user_data = "{\n" +
                "  \"first_name\": \"Bbsegungtetsts\",\n" +
                "  \"last_name\": \"JSONBOLOFINDE01\",\n" +
                "  \"token\": \"User_MQ_02\",\n" +
                "  \"email\": \"mqusertoken12@gmail.com\",\n" +
                "  \"password\": \"P@ssw0rd\"\n" +
                "}";

        var user_response = APIRequests.postRequests("users",user_data);

        user_response.prettyPrint();
        System.out.println(user_response.statusCode());
        System.out.println(user_response.getBody().jsonPath().get("token").toString());

    }

    @Test
    public void createCardProduct(){

        String card_product_data = "{\n" +
                "  \"name\": \"MQ_PHYSICAL_CARD_01\",\n" +
                "  \"start_date\": \"2024-01-02\"\n" +
                "}";

        JSONObject card_product_data_jsonObject = new JSONObject();
        card_product_data_jsonObject.put("name","MQ_PHYSICAL_CARD_03");
        card_product_data_jsonObject.put("start_date","2024-01-03");



        var card_product_response = APIRequests.postRequests("cardproducts",card_product_data_jsonObject.toJSONString());

        String card_product_token = card_product_response.getBody().jsonPath().get("token").toString();
        System.out.println(card_product_token);
        System.out.println(card_product_response.statusCode());
        card_product_response.prettyPrint();
    }

    @Test
    public void TestCreateCard(){
        //todo create cards
        JSONObject card_data_json = new JSONObject();

        String user_token = "User_MQ_06";
        String card_product_token = "ATM_card_02";
        card_data_json.put("card_product_token",card_product_token);
        card_data_json.put("user_token",user_token);

        var response = APIRequests.postRequests("cards",card_data_json.toJSONString());

        System.out.println(response.statusCode());
        response.prettyPrint();
    }


    @Test
    public void testFundingACard(){
        JSONObject fundingData = new JSONObject();
        fundingData.put("user_token","User_MQ_06");
        fundingData.put("amount","100.00");
        fundingData.put("currency_code","USD");
        fundingData.put("funding_source_token","sanbox_program_funding");

        var response = APIRequests.postRequests("gpaorders",fundingData.toJSONString());

        response.prettyPrint();
    }
    }

