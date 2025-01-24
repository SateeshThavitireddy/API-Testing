package ApiTesting.ApiTesting.testing;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class AppTest {
	
	RequestSpecification requestSpecification;
	Response response;
	ValidatableResponse validatableResponse;
	
	/*
	 * @BeforeTest public void initilzeData() { requestSpecification = with().
	 * baseUri("http://dummy.restapiexample.com/api/v1/employees"); }
	 * 
	 * @Test public void verifyStatusCode() { requestSpecification =
	 * RestAssured.given(); response = requestSpecification.get(); String
	 * resquestString = response.prettyPrint();
	 * System.out.println("Response Details is : "+ resquestString);
	 * 
	 * validatableResponse = response.then(); validatableResponse.statusCode(200);
	 * validatableResponse.statusLine("HTTP/1.1 200 OK"); }
	 */
    @Test
    public void verifyStatusCode() {
 
        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
 
        // Create a request specification
        requestSpecification = RestAssured.given();
 

        response = requestSpecification.get();
 
        // Let's print response body.
        String resString = response.prettyPrint();
        System.out.println("Response Details : " + resString);

        validatableResponse = response.then();
 
        // Get status code
        validatableResponse.statusCode(200);
 
        // Check status line is as expected
        validatableResponse.statusLine("HTTP/1.1 200 OK");
 
    }
    
    @Test
    public void getRequestUsingBDD() {
    	
    	given()
    	.when()
    	.get("https://reqres.in/api/users/2")
    	.then()
    	.statusCode(200).statusLine("HTTP/1.1 200 OK")
    	.body("data.first_name",equalTo("Janet"));
    	
    }
}
