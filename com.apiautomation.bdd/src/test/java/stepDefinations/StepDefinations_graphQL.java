package stepDefinations;

import org.json.simple.JSONObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class StepDefinations_graphQL {
	public static String jwt;
	RequestSpecification res;
	
	@Given("user is authenticated and generated jwt")
	public void user_is_authenticated_and_generated_jwt() {
		RestAssured.baseURI="http://10.103.2.14/";
		RequestSpecification res = given().header("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("query", "mutation{\n  login(username:\"oijiqntkjbr@gmail.com\" password:\"password1\"){\n    jwtInfo{\n      jwt\n    }\n    \n  }\n  \n}\n");
		Response response = res.body(json).post().then().extract().response();
		jwt = response.jsonPath().get("data.login.jwtInfo.jwt").toString();
		
		
	}

	@When("user hits customer info query")
	public void user_hits_customer_info_query() {
		res = given().header("Content-Type","application/json");
		JSONObject json = new JSONObject();
	   json.put("query", "query{\n  accountsInfo{\n    accounts{\n      accountId\n      obfuscatedAccountId\n    }\n  }\n}");
	   
	   Response response = res.header("Authorization",jwt).body(json).post().then().extract().response();
	   String accountID = response.jsonPath().get("data.accountsInfo.accounts.accountID").toString();
	   System.out.println(accountID);
	}

	@Then("API response is status code is {int}")
	public void api_response_is_status_code_is(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		 System.out.println("pass");
		   
	}

	@Then("Validate Response")
	public void validate_Response() {
	    // Write code here that turns the phrase above into concrete actions
		 System.out.println("pass");
		   
	}


}
