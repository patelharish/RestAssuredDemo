package AuthenticationOrAuthorization;

import org.testng.annotations.Test;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizationDemo {

	@Test
	public void BasicAuth() {
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.baseUri("https://httpbin.org");
		reqSpec.basePath("/basic-auth/foo/bar");
		
		// basic username = "foo", password = "bar"
		Response res = reqSpec.auth().basic("foo", "bar").get();
		
		System.out.println("Response status: "+res.statusLine());
	}
	
	@Test
	public void DigestAuth() {
		
		// https://httpbin.org/digest-auth/undefined/harish/patel
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.baseUri("https://httpbin.org");
		reqSpec.basePath("/digest-auth/undefined/harish/patel");
		
		// basic username = "foo", password = "bar"
		Response res = reqSpec.auth().digest("harish", "patel").get();
		
		Assert.assertEquals(res.statusCode(), 200, "check for status code");
		System.out.println("Digest Auth Response status: "+res.statusLine());
		System.out.println("Digest auth Response body: "+res.body().asString());
	}
}
