package ApiTesting.ApiTesting.testing;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AppTest {
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
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
    	Response response;
    	ValidatableResponse validatableResponse;
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
    
    
    @BeforeMethod
    public void initializeValues() {
    	String json = ""
    			+ "{\r\n"
    			+ "    \"name\": \"morpheus\",\r\n"
    			+ "    \"job\": \"leader\"\r\n"
    			+ "}"
    			+ "";
    	requestSpecification = given().
    			               baseUri("https://reqres.in/api")
    			               .header("contentType","application/json")
    			               .body(json.toString());
    	responseSpecification =  RestAssured.expect().statusCode(201);
    }
    
    @Test
    public void postRequestWithBDD() {

    	given().
    	spec(requestSpecification)
    	.when()
    	.post("users")
    	.then().
    	spec(responseSpecification);
    }
    
    
    @Test
    public void postRequestWithOutBDD(){
    	String json = ""
    			+ "{\r\n"
    			+ "    \"name\": \"Sateesh\",\r\n"
    			+ "    \"job\": \"leader\"\r\n"
    			+ "}"
    			+ "";
    	
    	RestAssured.baseURI ="https://reqres.in/api/users";
    	requestSpecification = RestAssured.given();
    	requestSpecification.contentType(ContentType.JSON);
    	requestSpecification.body(json.toString());
    	
    	
    	response = requestSpecification.post();
    	String responseString = response.prettyPrint();
    	validatableResponse = response.then();
    	validatableResponse.statusCode(201);
    	validatableResponse.statusLine("HTTP/1.1 201 Created");
    	
    }
}
