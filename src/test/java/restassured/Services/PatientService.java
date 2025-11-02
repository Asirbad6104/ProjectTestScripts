package restassured.Services;

import io.restassured.RestAssured;
import restassured.Test.BaseTest;
import io.restassured.response.Response;

public class PatientService extends BaseTest {
    public Response getPrescriptions(String token) {
        Response response = RestAssured
                .given(requestSpecification)
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer " + token)
                .get("/prescriptions");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());
        return response;
    }


}
