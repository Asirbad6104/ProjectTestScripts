package restassured.Services;

import io.restassured.response.Response;
import restassured.Test.BaseTest;

public class RegisterService extends BaseTest {
    public Response postRequest(String endpoint,Object body)
    {
        return doPost(endpoint,body);
    }
}

