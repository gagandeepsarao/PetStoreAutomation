package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority = 1,dataProvider = "Data", dataProviderClass= DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph) {
		
		User payload = new User();
		payload.setId(Integer.parseInt(userID));
		payload.setUsername(userName);
		payload.setFirstName(fname);
		payload.setLastName(lname);
		payload.setEmail(useremail);
		payload.setPassword(pwd);
		payload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(payload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass= DataProviders.class)
	public void testDeleteUserByName(String userName) {
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
