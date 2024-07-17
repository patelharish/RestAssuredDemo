import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Test_PutMethod {

	@Test
	public void test04() {
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Anuj");
		jsonData.put("job", "SDE");
		
		RestAssured.baseURI = "https://reqres.in/api/user/466";
		RestAssured.given().header("Content-type", "application/json")
		.contentType(ContentType.JSON).
		body(jsonData.toJSONString())
		.when().put()
		.then().statusCode(200).log().all();
	}
}
