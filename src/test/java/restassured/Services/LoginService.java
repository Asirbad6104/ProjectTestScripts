package restassured.Services;

import io.restassured.response.Response;
import restassured.Test.BaseTest;

public class LoginService extends BaseTest {
    public Response postRequest(String endpoint, Object body)
    {
        return doPost(endpoint,body);
    }
}
