package restassured.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
    public RequestSpecification requestSpecification;
    public BaseTest()
    {
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecification=requestSpecBuilder
                .setBaseUri("http://localhost:8081")
                .setContentType(ContentType.JSON)
                .build();
    }
    protected Response doGet(String endpoint)
    {
        return RestAssured
                .given(requestSpecification)
                .when()
                .get(endpoint)
                .then().extract().response();
    }
    protected Response doPost(String endpoint,Object body)
    {
        return RestAssured
                .given(requestSpecification)
                .log().all()
                .body(body)
                .when()
                .post(endpoint)
                .then().log().all()
                .extract().response();
    }
}
