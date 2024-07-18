package OAuth2.oAuthorization;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizationOAuth {

	// https://api-m.sandbox.paypal.com/v1/oauth2/token
	// client id :
	// ATlg_cJ8hXtS48p-SDDsLccImBrziq-aKGIXnBZ7LhUt2r34PLr_1W8-otWvCKDGBqEdjOMCzmZ3vIRX
	// secret code :
	// EBrnpjnsk8IdA003uy1bN_5maOu5GaI3UawXfxuVI0YLfwcdpnj_B7mUWjRpG6C_9iSHcb2uxZx6DqHY

	static String accessToken;

	@Test
	public void GetAccessToken() {

		// create reqspec.
		RequestSpecification reqSpec = RestAssured.given();

		// specify url
		reqSpec.baseUri("https://api-m.sandbox.paypal.com");
		reqSpec.basePath("/v1/oauth2/token");

		String clientId = "ATlg_cJ8hXtS48p-SDDsLccImBrziq-aKGIXnBZ7LhUt2r34PLr_1W8-otWvCKDGBqEdjOMCzmZ3vIRX";
		String secretCode = "EBrnpjnsk8IdA003uy1bN_5maOu5GaI3UawXfxuVI0YLfwcdpnj_B7mUWjRpG6C_9iSHcb2uxZx6DqHY";
		// basic auth
		Response res = reqSpec.auth().preemptive().basic(clientId, secretCode).param("grant_type", "client_credentials")
				.post();

		res.prettyPrint();

		System.out.println("Response code: " + res.statusCode());
		System.out.println("Response Line: " + res.statusLine());
		System.out.println(res.getBody().asString());

		Assert.assertEquals(res.statusCode(), 200, "check status code");

		// get access token

		accessToken = res.getBody().path("access_token");

		System.out.println("Access_token: " + accessToken);

	}

	@Test(dependsOnMethods = "GetAccessToken")
	public void ListInvoice() {

		Response res2 = RestAssured.given().auth().oauth2(accessToken).queryParam("page", "3")
				.queryParam("page_size", "4").queryParam("total_count_required", "true")
				.get("https://api-m.sandbox.paypal.com/v1/invoicing/invoices");
		
		System.err.println("-----------List Invoice-----------");
		res2.prettyPrint();
		
		System.out.println("Response code: " + res2.statusCode());
		System.out.println("Response Line: " + res2.statusLine());

	}

}
