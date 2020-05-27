package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefination m=new StepDefination();
		if(StepDefination.place_id==null) {
			
		
		m.addPlacePayloadWith("Anurag", "French", "Russia");
		m.userCallWithPostHttpRequest("addPlaceAPI", "POST");
		m.verifyPlace_idCreatedMapsToUsing("Anurag", "getPlaceAPI");
		}
	}

}
