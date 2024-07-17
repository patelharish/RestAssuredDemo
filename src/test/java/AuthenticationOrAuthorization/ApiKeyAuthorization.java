package AuthenticationOrAuthorization;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiKeyAuthorization {

	// https://api.openweathermap.org/data/2.5/weather?q={city id}&appid={API key}
	@Test
	public void GetWheatherDataByCity() {
		RequestSpecification reqSpec = RestAssured.given();

		reqSpec.baseUri("https://api.openweathermap.org");
		reqSpec.basePath("/data/2.5/weather");

		// for Api key visit this website and signup you will get Api key:
		// https://home.openweathermap.org/api_keys

		reqSpec.queryParam("q", "delhi").queryParam("appid", "d140957ef62766b8c6ea39e593b59261");

		Response res = reqSpec.get();

		Assert.assertEquals(res.statusCode(), 200, "check for status code");
		System.out.println("Request status line: " + res.statusLine());
		System.out.println("Request status body: " + res.body().asString());

	}
}
