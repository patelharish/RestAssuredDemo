import org.testng.annotations.Test;

import static io.restassured.RestAssured.*; // Removed RestAssured class by using this pcg
import io.restassured.response.Response;
import junit.framework.Assert;

public class Test_GetMethod {

	@Test
	public void test01() {
		Response res = get("https://reqres.in/api/users?page=2");
		System.out.println("Response code: " +res.getStatusCode());
		System.out.println("Respnse body: " +res.getBody().asString());
		System.out.println("Response time: " +res.getTime());
		System.out.println("Response header: " +res.getHeader("Content-Type"));
		
		// validate status code
		
		int expectedstatuscode = 200;
		int ActualStatuscode = res.getStatusCode();
		
		Assert.assertEquals(expectedstatuscode, ActualStatuscode);
	}
	
	// BDD style
	@Test
	public void test02() {
		//given,when,then
		
		baseURI = "https://reqres.in/api/users";
	    given().queryParam("page", "2")
		.when().get()
		.then().statusCode(200);
		
	}
}
