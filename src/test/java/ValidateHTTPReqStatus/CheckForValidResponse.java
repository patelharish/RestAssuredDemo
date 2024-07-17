package ValidateHTTPReqStatus;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CheckForValidResponse {

	// By using validation: Testng Assertion
	@Test(enabled = false)
	public void GetSingleUser() {

		// specify base uri
		RestAssured.baseURI = "https://reqres.in/api/users/2";

		// Get request spec of the request
		RequestSpecification requestSpec = RestAssured.given();

		// call get method
		Response res = requestSpec.get();

		// get rresponse code
		int Actualstatuscode = res.getStatusCode();

		// validate actual status code with expected
		Assert.assertEquals(Actualstatuscode/* Actual status code */, 200/* Expected status code */,
				"Correct Status code recieved");

		String statusLine = res.getStatusLine();

		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Incorrect statusLine written");

	}

	// By using validation: validatable response 
	
	@Test(enabled = false)
	public void GetSingleUserValidatableResponse() {

		// specify base uri
		RestAssured.baseURI = "https://reqres.in/api/users/2";

		// Get request spec of the request
		RequestSpecification requestSpec = RestAssured.given();

		// call get method
		Response res = requestSpec.get();

		ValidatableResponse validateRes = res.then();
		
		// validate status code
		validateRes.statusCode(200);
		
		//Status line 
		validateRes.statusLine("HTTP/1.1 200 OK");
	}
	
	// BDD formate
	@Test
	public void GetSingleUserBDDStyle() 
	{
		RestAssured.given()  // to remove RestAssured class you need to put pcg in static and make end with *
		
		.when()
		   .get("https://reqres.in/api/users/2")
		
		.then()
		    .statusCode(200)
		    .statusLine("HTTP/1.1 200 OK");
	}
}
