package apitests.MQ.MQtests.serialization;

import apitests.MQ.apiutils.APIRequests;
import models.User;
import org.testng.annotations.Test;

public class UserTests {

    @Test
    public void getAllUsers(){
        var user = APIRequests.getAllRequests("users").as(User.class);

        System.out.println(user.getData());
    }
}
