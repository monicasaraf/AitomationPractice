Feature: Validating Place API's
@AddPlace
Scenario Outline: Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" request
	Then API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
Examples:
		
		|name  | language | address           |
		|AHouse| English  | world cross center|
		|BHouse| French   | Sea Cross Center  |
@DeletePlace	
Scenario:  Verify if delete place functionality is working
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" request
	Then API call is success with status code 200
	And "status" in response body is "OK"