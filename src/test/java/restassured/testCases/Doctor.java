package restassured.testCases;


import io.cucumber.java.en.*;
import io.restassured.response.Response;
import restassured.Services.DoctorService;
import restassured.Services.LoginService;
import restassured.models.UserLogin;
import restassured.models.DoctorResponse;
import restassured.models.DoctorResponse.Medication;
import restassured.models.DoctorMessage;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class Doctor {

    private String token;
    private Response response;

    @Given("the doctor login credentials are provided")
    public void the_doctor_login_credentials_are_provided(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        UserLogin userLogin = new UserLogin(data.get("username"), data.get("password"));

        LoginService loginService = new LoginService();
        Response loginResponse = loginService.postRequest("/auth/login", userLogin);

        assertEquals(loginResponse.getStatusCode(), 200, "Login failed");
        token = loginResponse.jsonPath().getString("token");
        assertNotNull(token, "Token not found");
    }

    @When("the doctor sends a prescription to the first message")
    public void the_doctor_sends_a_prescription_to_the_first_message() {
        DoctorService doctorService = new DoctorService();
        List<DoctorMessage> messages = doctorService.getDoctorMessages(token);

        assertFalse(messages.isEmpty(), "No messages found for doctor");

        int requestId = messages.get(0).getId();

        DoctorResponse prescription = new DoctorResponse();
        prescription.setDiagnosis("RestAssured");

        Medication med = new Medication();
        med.setMedicationName("Check1");
        med.setDosageTiming("Evening");
        med.setDurationDays(2);
        med.setStartDate("2025-11-03");

        prescription.setMedications(List.of(med));

        response = doctorService.sendPrescription(token, requestId, prescription);
    }

    @Then("the prescription should be successfully sent")

    public void the_prescription_should_be_successfully_sent() {
        assertEquals(response.getStatusCode(), 200, "Prescription submission failed");
        String responseBody = response.getBody().asString();
        System.out.println("Prescription Response: " + responseBody);
        assertTrue(responseBody.contains("\"id\":"), "Prescription ID not found in response");
    }

}

