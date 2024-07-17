package AuthenticationOrAuthorization;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class AuthorizationBearerDemo {

	//https://gorest.co.in/public/v2/users
	
	@Test
	public void BearerToken() {
		RequestSpecification resSpec = RestAssured.given();
		
		resSpec.baseUri("https://gorest.co.in");
		resSpec.basePath("/public/v2/users");
			
		/*{
			"name":"Anuj",
			"email":"anuj@gmail.com",
			"gender":"male",
			"status":"Active"
			} */
		
		JSONObject payload = new JSONObject();
		payload.put("name", "dharni");
		payload.put("email", "dharni@gmail.com");
		payload.put("gender", "male");
		payload.put("status", "Active");
		
		String AuthToken = "Bearer 0e0e44047e8a75640ab93f7f660e5b1cb22bd51ad7980e0312cd740ff790080d";
		
		resSpec.headers("Authorization", AuthToken).
		contentType(ContentType.JSON).
		body(payload.toJSONString());
		
		// perform post request
		Response res = resSpec.post();
		
		Assert.assertEquals(res.statusCode(), 201, "check for status code");
		System.out.println("Request status line: "+res.statusLine());
		System.out.println("Request status body: "+res.body().asString());		
		
		
		
	}
}
