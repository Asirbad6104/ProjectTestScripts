package restassured.testCases;

import com.google.gson.Gson;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import restassured.Services.LoginService;
import restassured.Services.RegisterService;
import restassured.models.UserLogin;

import java.util.Map;
import io.cucumber.datatable.DataTable;

import static org.testng.Assert.assertTrue;

public class Login {

    private UserLogin userLogin;
    private Response response;

    @Given("the user login credentials are provided")
    public void the_user_login_credentials_are_provided(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        userLogin = new UserLogin(data.get("username"), data.get("password"));
    }

    @When("the user attempts to login")
    public void the_user_attempts_to_login() {
        LoginService rs = new LoginService();
        System.out.println(userLogin.getUsername()+" "+userLogin.getPassword());
        response = rs.postRequest("/auth/login", userLogin);
    }


    @Then("the user should receive a login success message")
    public void the_user_should_receive_a_login_success_message() {
        int statusCode = response.getStatusCode();
        String responseBody = response.asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        assertTrue(statusCode == 200, "Login failed. Status code: " + statusCode);

        if (responseBody != null && !responseBody.trim().isEmpty()) {
            String token = response.jsonPath().getString("token");
            assertTrue(token != null && !token.isEmpty(), "Token not found in response.");
        } else {
            throw new AssertionError("Empty response body. Cannot parse token.");
        }
    }

}
