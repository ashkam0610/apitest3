package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import  io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	static ResourceBundle getURL()
	
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		Response res = given()
		      .contentType(ContentType.JSON)
		      .accept(ContentType.JSON)
		      .body(payload)
		.when().post(post_url);
		return res;
	}
	public static Response ReadUser(String Username)
	{
		String get_url=getURL().getString("get_url");
		Response res = given()
		      .pathParam("username",Username)
		.when()
		.get(get_url);
		
		return res;
	}
	
	public static Response updateUser(User payload, String Username)
	{
		String put_url=getURL().getString("put_url");
		Response res = given()
		      .contentType(ContentType.JSON)
		      .accept(ContentType.JSON)
		      .pathParam("username",Username)
		      .body(payload)
		.when().put(put_url);
		
		return res;
	}
	
	public static Response DeleteUser(String Username)
	{
		String delete_url=getURL().getString("delete_url");
		Response res = given()
		      .pathParam("username",Username)
		.when()
		.delete(delete_url);
		
		return res;
	}
}
