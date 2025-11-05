package restassured.Services;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import restassured.Test.BaseTest;
import restassured.models.DoctorResponse;
import restassured.models.DoctorMessage;

import java.util.Arrays;
import java.util.List;

public class DoctorService extends BaseTest {

    public List<DoctorMessage> getDoctorMessages(String token) {
        Response response = RestAssured
                .given(requestSpecification)
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer " + token)
                .get("/doctor/messages");

        System.out.println("Messages Response: " + response.asString());
        return Arrays.asList(response.getBody().as(DoctorMessage[].class));
    }

    public Response sendPrescription(String token, int requestId, DoctorResponse prescription) {
        System.out.println("Sending prescription for request ID: " + requestId);

        return RestAssured
                .given(requestSpecification)
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer " + token)
                .body(prescription)
                .post("/doctor/respond/" + requestId);
    }

    public Response deletePrescription(int prescriptionId) {
        return doDelete("/prescriptions/" + prescriptionId);
    }
}

