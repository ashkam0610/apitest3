package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import  io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		Response res = given()
		      .contentType(ContentType.JSON)
		      .accept(ContentType.JSON)
		      .body(payload)
		.when().post(Routes.post_url);
		return res;
	}
	public static Response ReadUser(String Username)
	{
		Response res = given()
		      .pathParam("username",Username)
		.when()
		.get(Routes.get_url);
		
		return res;
	}
	
	public static Response updateUser(User payload, String Username)
	{
		Response res = given()
		      .contentType(ContentType.JSON)
		      .accept(ContentType.JSON)
		      .pathParam("username",Username)
		      .body(payload)
		.when().put(Routes.update_url);
		
		return res;
	}
	
	public static Response DeleteUser(String Username)
	{
		Response res = given()
		      .pathParam("username",Username)
		.when()
		.delete(Routes.delete_url);
		
		return res;
	}
}
