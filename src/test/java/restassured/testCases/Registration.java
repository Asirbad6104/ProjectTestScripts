package restassured.testCases;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import restassured.Services.RegisterService;
import restassured.models.UserRegistration;

import java.util.Map;
import io.cucumber.datatable.DataTable;

import static org.testng.Assert.assertTrue;

public class Registration {

    private UserRegistration user;
    private Response response;

    @Given("the user details are provided")
    public void the_user_details_are_provided(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        user = new UserRegistration(
                data.get("name"),
                data.get("password"),
                data.get("email"),
                data.get("role")
        );
    }

    @When("the user is successfully registered")
    public void the_user_is_successfully_registered() {
        RegisterService rs = new RegisterService();
        response = rs.postRequest("/auth/register", user);
        System.out.println("Response: " + response.asString());
    }

    @Then("the user should receive a success message")
    public void the_user_should_receive_a_success_message() {
        assertTrue(response.asString().toLowerCase().contains("success"), "Expected success message not found.");
    }
}
