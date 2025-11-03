package restassured.testCases;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import restassured.Services.PatientService;
import restassured.Services.LoginService;
import restassured.models.UserLogin;
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

    @When("the user sends a request to get prescriptions")
    public void the_user_sends_a_request_to_get_prescriptions() {
        PatientService patientService = new PatientService();
        response = patientService.getPrescriptions(token);
    }

    @Then("the user should receive a list of prescriptions")
    public void the_user_should_receive_a_list_of_prescriptions() {
        assertEquals(response.getStatusCode(), 200, "Failed to fetch prescriptions");
        assertTrue(response.jsonPath().getList("$").size() > 0, "No prescriptions found");
    }
}