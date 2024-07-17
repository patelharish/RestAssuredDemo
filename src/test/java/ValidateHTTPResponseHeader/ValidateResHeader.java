package ValidateHTTPResponseHeader;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class ValidateResHeader {

	@Test
	public void GetSingleUser()
	{
		// Get request specification
		RequestSpecification reqSpec = RestAssured.given();
		
		// specify base uri
		reqSpec.baseUri("https://reqres.in");
		reqSpec.basePath("/api/users/2");
		
		// create get request
		Response res = reqSpec.get();
		
		// validate response header single value
		String contentType = res.header("Content-Type");
		
		//System.out.println(contentType);
		
		// validate all response headers list
		Headers headersList = res.getHeaders();
		
		for (Header header:headersList) {
			System.out.println(header.getName() +":"+ header.getValue());
		}
		
		// Validate header contents
		Assert.assertEquals(contentType, "application/json; charset=utf-8","header content type mismatch");
		
	}
}
