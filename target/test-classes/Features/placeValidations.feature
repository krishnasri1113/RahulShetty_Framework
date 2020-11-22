Feature: Validating place API's
@AddPlace @Regression
Scenario Outline: verify if place is successfully added using AddAPI
Given Add Place payload with "<name>" "<language>" "<address>"
When user calls the "AddPlaceAPI" with "POST" request
Then the API call get success code with 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
|name   | language  |address|
|siri   |Telugu     |Hyderabad |
|santhu |English    |USA        |

@DeletePlace @Regression
Scenario: Verify if delete place functionality is working
Given  Add DeletePlaceAPI payload
When   user calls the "deletePlaceAPI" with "POST" request
Then  the API call get success code with 200
And    "status" in response body is "OK"
