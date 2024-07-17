package ValidateJsonRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class ValidateJSONResBody {

	// https://reqres.in/api/users?page=2
	@Test
	public void UserListResBody() 
	{
		
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.baseUri("https://reqres.in");
		reqSpec.basePath("/api/users?page=2");
		
		// create get request
		Response res = reqSpec.get();
		
		// read response body
		ResponseBody responseBody = res.getBody();
		
		// convert response body to string
		String resBody = responseBody.asString();
		
		//print response body 
		System.out.println(resBody);
		
		Assert.assertEquals(resBody.contains("george.bluth@reqres.in"),true,"check for presense on George mail");
		
		// get JSON path view of response body
		JsonPath jsonPathView = responseBody.jsonPath();
		
		// data[4].first_name   find this path to jsonpath finder in google
		String firstName = jsonPathView.get("data[0].first_name");
		
		System.out.println("email address: "+jsonPathView.get("data[0].email"));
		
		Assert.assertEquals(firstName, "George","check for presense of firstname");
		
	}
}
