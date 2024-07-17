package QueryParameter;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class QueryParamDemo {

	@Test
	public void filterData() 
	{
		RequestSpecification requestSpec = RestAssured.given();
		
		requestSpec.baseUri("https://reqres.in/");
		requestSpec.basePath("api/users");
		requestSpec.queryParam("page", 2).queryParam("id", 10);
		
		// perform get request
		Response res = requestSpec.get();
		
		String responseStr = res.asString();
		
		System.out.println("Response body: "+responseStr);
		
		JsonPath jsonPathview = res.jsonPath();
		
		String lastName = jsonPathview.get("data.last_name");
		String email = jsonPathview.get("data.email");
		
		System.out.println(lastName);
		System.out.println(email);
		
		Assert.assertEquals(lastName, "Fields","check for presense of lastName");
		Assert.assertEquals(email, "byron.fields@reqres.in","check for presense of email");
	}
}
