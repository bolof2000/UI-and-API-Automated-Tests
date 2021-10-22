package utils.data;

import com.github.javafaker.Faker;
import com.mongodb.util.JSON;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Locale;

public class DataCreation {

    public static Locale locale = new Locale("en_us");
    static Faker faker = new Faker(locale);

    public static JSONObject dataGenSignup() throws IOException {

        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        JSONObject data = new JSONObject();
        JSONObject user = new JSONObject();
        user.put("user",data);
        data.put("username", username);
        data.put("email", email);
        data.put("password", password);

        return user;
    }


    public static JSONObject cardProductAutoGenData(){
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        JSONObject data = new JSONObject();
        JSONObject cardProduct = new JSONObject();
        cardProduct.put("user",data);
        data.put("username", username);
        data.put("email", email);
        data.put("password", password);

        return cardProduct;
    }

    public static JSONObject cardAutoData(){
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        JSONObject data = new JSONObject();
        JSONObject card = new JSONObject();
        card.put("user",data);
        data.put("username", username);
        data.put("email", email);
        data.put("password", password);

        return card;
    }
}
