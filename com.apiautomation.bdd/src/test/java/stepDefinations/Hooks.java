package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {


	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{		//execute this code only when place id is null
		//write a code that will give you place id
		
		StepDefinations m =new StepDefinations();
		if(StepDefinations.place_id==null)
		{
		if(m.place_id==null){
		m.add_Place_Payload_with("Shetty", "French", "Asia");
		m.user_calls_with_request("AddPlaceAPI", "POST");
		m.verify_place_Id_created_maps_to_using("Monica","getPlaceAPI");
		}
		}
		
		

	}
}
