package restassured.Services;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import restassured.Test.BaseTest;
import io.restassured.response.Response;
import restassured.models.PatientDashboard;

public class PatientService extends BaseTest {


    public Response submitPatientForm(String token, PatientDashboard formRequest) {
        System.out.println("Request Payload: " + new com.google.gson.Gson().toJson(formRequest));

        Response response = RestAssured
                .given(requestSpecification)
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer " + token)
                .body(formRequest)
                .post("/patient/submit-form");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());
        return response;
    }




}
