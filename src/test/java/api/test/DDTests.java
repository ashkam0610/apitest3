package api.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;
import junit.framework.Assert;

public class DDTests {
	
	@SuppressWarnings("deprecation")
	@Test(priority=1, dataProvider="Data", dataProviderClass=Dataproviders.class)
	public void testPostuser(String userID,String userName, String fname, String lname,String email, String pwd,String ph)
	{
		 User userPayload = new User();
		 userPayload.setId(Integer.parseInt(userID));
		 userPayload.setUsername(userName);
		 userPayload.setFirstName(fname);
		 userPayload.setLastName(lname);
		 userPayload.setEmail(email);
		 userPayload.setPassword(pwd);
		 userPayload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=Dataproviders.class)
	public void testPostuser(String userName)
	{
		Response response = UserEndPoints.DeleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}


}
