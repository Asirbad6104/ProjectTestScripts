
package restassured.testCases;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import restassured.Services.PatientService;
import restassured.Services.LoginService;
import restassured.models.UserLogin;
import restassured.models.PatientDashboard;
import io.cucumber.datatable.DataTable;

import java.util.Map;

import static org.testng.Assert.*;


public class Patient {

    private String token;
    private Response response;

    @Given("the user login credentials are provided for prescription access")
    public void the_user_login_credentials_are_provided_for_prescription_access(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        UserLogin userLogin = new UserLogin(data.get("username"), data.get("password"));

        LoginService loginService = new LoginService();
        Response loginResponse = loginService.postRequest("/auth/login", userLogin);

        assertEquals(loginResponse.getStatusCode(), 200, "Login failed");
        token = loginResponse.jsonPath().getString("token");
        assertNotNull(token, "Token not found");
    }

    @When("the user submits the consultation form with details")
    public void the_user_submits_the_consultation_form_with_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        PatientDashboard formRequest = new PatientDashboard();
        formRequest.setDiseaseType(data.get("specialist").trim());
        formRequest.setSeverity(data.get("level"));
        formRequest.setContent(data.get("description"));

        PatientService patientService = new PatientService();
        response = patientService.submitPatientForm(token, formRequest);
    }

    @Then("the user should receive a confirmation for consultation")
    public void the_user_should_receive_a_confirmation_for_consultation() {
        assertEquals(response.getStatusCode(), 200, "Form submission failed");
        String responseBody = response.getBody().asString();
        System.out.println("Raw Response Body: " + responseBody);

        assertEquals(response.getStatusCode(), 200, "Form submission failed");
        assertTrue(responseBody.contains("Patient request created"), "Expected confirmation message not found");

    }
}  
