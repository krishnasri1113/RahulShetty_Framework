package StepDefinations;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void beforescenario() throws Exception
	{
		StepDefination sd=new StepDefination();
		if(StepDefination.Place_id==null)
		{
		sd.add_Place_payload_with("sai", "Hindi", "India");
		sd.user_calls_the_with_request("AddPlaceAPI", "POST");
		sd.verify_place_Id_created_maps_to_using("sai", "getPlaceAPI");
	}
	}

}
