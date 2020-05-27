package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

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
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	static String place_id;
	TestDataBuild data=new TestDataBuild();
	

	@Given("Add Place Payload with {string} {string} {string}")
	public void addPlacePayloadWith(String name, String language, String address)  throws IOException {
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));

	}

	@When("User call {string} with {string} Http Request")
	public void userCallWithPostHttpRequest(String resource,String method) {
		
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))	
		response = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());

	}

	@Then("The API call success with status cosde as {int}")
	public void theAPICallSuccessWithStatusCosdeAs(Integer statuscode) {

		assertEquals(response.statusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void inResponseBodyIs(String keyValue, String ExpectedValue) {
		
		
		assertEquals(getJsonPath(response,keyValue), ExpectedValue);

	}
	
	@Then("Verify place_id created maps to {string} using {string}")
	public void verifyPlace_idCreatedMapsToUsing(String ExpectedName,String resource) throws IOException {
	    
		place_id=getJsonPath(response,"place_id");
		res= given().spec(requestSpecification()).queryParam("place_id", place_id);
				userCallWithPostHttpRequest(resource,"GET");
				String actualName=getJsonPath(response,"name");
				assertEquals(actualName, ExpectedName);
	}
	
	
	
	@Given("Delete Place Payload")
	public void deletePlacePayload() throws IOException {
		
		res= given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	    
	}

}
