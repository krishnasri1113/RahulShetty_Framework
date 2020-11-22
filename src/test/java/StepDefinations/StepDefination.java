package StepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import static  io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class StepDefination extends Utils {
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	TestDataBuild data=new TestDataBuild();
	Response res;
	static String Place_id;
	

	@Given("Add Place payload with {string} {string} {string}")
	public void add_Place_payload_with(String name, String language, String address) throws Exception {
		reqspec=given().spec(requestspecification()).body(data.addplacepayload(name, language, address));
	}
	
	  

	@When("user calls the {string} with {string} request")
	public void user_calls_the_with_request(String resource, String method) {
		APIResources apiresources=APIResources.valueOf(resource);
		System.out.println(apiresources.getResource());
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		{
		res=reqspec.when().post(apiresources.getResource()).then().spec(resspec).extract().response();
		}
		else if(method.equalsIgnoreCase("GET"))
		{
			res=reqspec.when().get(apiresources.getResource()).then().spec(resspec).extract().response();
		}
		
		}
	    
	

	@Then("the API call get success code with {int}")
	public void the_API_call_get_success_code_with(Integer int1) {
	    assertEquals(res.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expected) {
	    assertEquals(getJsonPath(res, key),expected);

}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedvalue, String resource) throws Exception {
		 Place_id=getJsonPath(res, "place_id");
		reqspec=given().spec(requestspecification()).queryParam("place_id",Place_id);
		user_calls_the_with_request(resource, "GET");
		String actualvalue=getJsonPath(res,"name");
		assertEquals(actualvalue, expectedvalue);
	   
}

@Given("Add DeletePlaceAPI payload")
public void add_DeletePlaceAPI_payload() throws Exception {
    reqspec=given().spec(requestspecification()).body(data.deleteplaceAPI(Place_id));
}


}