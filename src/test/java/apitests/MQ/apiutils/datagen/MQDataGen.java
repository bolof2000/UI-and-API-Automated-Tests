package apitests.MQ.apiutils.datagen;

import com.github.javafaker.Faker;
import jodd.json.meta.JSON;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

public class MQDataGen {

    public static Locale locale = new Locale("en_us");
    static Faker faker = new Faker(locale);

    public static JSONObject createUserData() throws IOException {

        JSONObject user_data = new JSONObject();
        user_data.put("first_name",faker.name().firstName());
        user_data.put("last_name",faker.name().lastName());
        user_data.put("token",faker.internet().uuid());
        user_data.put("email",faker.internet().emailAddress());

        return user_data;

    }

}
