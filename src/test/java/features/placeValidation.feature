Feature: Validating Place API
@AddPlace @Regression
	Scenario Outline: Varify if place is being added sucessfully with add place API 
		Given Add Place Payload with "<name>" "<language>" "<address>"
		When User call "addPlaceAPI" with "POST" Http Request
		Then The API call success with status cosde as 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And Verify place_id created maps to "<name>" using "getPlaceAPI"
		
Examples:
					|name          |		|language|		|address				   |
					|	Baurav Home	 |    |English |		|Symphony Megapolis|
			#		|Saurabh Home  |    |German  |		|Hinjewadi Pune		 |
			
@DeletePlace @Smoke
	Scenario: Varify if delete place funcitionality is working 
		Given Delete Place Payload	
		When User call "deletePlaceAPI" with "POST" Http Request
		Then The API call success with status cosde as 200
		And "status" in response body is "OK"	
		