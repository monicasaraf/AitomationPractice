package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.And;
import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinations extends Utils {
	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		 res = given().spec(RequestSpecification()).body(data.addPlacePayload(name, language, address));
		
	}

	@When("user calls {string} with {string} request")
	public void user_calls_with_request(String resource, String method){
		APIResources apiResource = APIResources.valueOf(resource);
		apiResource.getResource();
		
		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		response = res.when().post(apiResource.getResource()).then().spec(resp).extract().response();
		else if(method.equalsIgnoreCase("GET"))
		response = res.when().get(apiResource.getResource()).then().spec(resp).extract().response();
		else if(method.equalsIgnoreCase("DELETE"))
			response = res.when().delete(apiResource.getResource()).then().spec(resp).extract().response();

	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	
	   // requestSpec
	     place_id=getJsonPath(response,"place_id");
		 res=given().spec(RequestSpecification()).queryParam("place_id",place_id);
		 user_calls_with_request(resource,"GET");
		  String actualName=getJsonPath(response,"name");
		  assertEquals(actualName,expectedName);
		 
	    
	}
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		res =given().spec(RequestSpecification()).body(data.deletePlacePayload(place_id));
	}

	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.statusCode(),200);
	}

	@And("{string} in response body is {string}")
	public void in_resonse_body_is(String status, String string2) {
	  	String respn = response.asString();
	  	JsonPath js = new JsonPath(respn);
	  	assertEquals(js.get(status).toString(), string2);
	}

	
	


}
