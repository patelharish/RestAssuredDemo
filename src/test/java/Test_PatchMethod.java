import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Test_PatchMethod {

	@Test
	public void test05() {
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Dharni");
		jsonData.put("job", "SQL Dev");
		
		RestAssured.baseURI = "https://reqres.in/api/user/466";
		RestAssured.given().header("Content-type", "application/json")
		.contentType(ContentType.JSON).
		body(jsonData.toJSONString())
		.when().patch()
		.then().statusCode(200).log().all();
	}
}
