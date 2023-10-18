package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() 
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("************* Creating user ***********");
		Response response = UserEndPoints.createUser(userPayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************* user is created ***********");
	}

	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("************* Reading user Info ***********");
		Response response = UserEndPoints.ReadUser(this.userPayload.getUsername());
		
		response.then().log().all();	
	//	response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************* User Info is displayed ***********");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		
		logger.info("************* updating user Info ***********");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//check up data after update
		
		Response AfterUpdateresponse = UserEndPoints.ReadUser(this.userPayload.getUsername());
		
		response.then().log().all();	
	//	response.statusCode();
		Assert.assertEquals(AfterUpdateresponse.getStatusCode(), 200);
		
		logger.info("*************updated  User Info is displayed ***********");
	}
	@Test(priority=4)
	public void testDeletetUserByName()
	{
		logger.info("************* User Info is Deleted ***********");
		Response response = UserEndPoints.DeleteUser(this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************* User  Deleted completed***********");
	}
	
}
