import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// Validate if Add Place API is working as expected
		// given - All input details
		// when - Submit the API
		// then - Validate the response
		// Content of the file to String -> content of file can convert into byte->Byte
		// data to string

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		/************************
		 * Post request to create new place id
		 *******************************/
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Files.readAllBytes(
						Paths.get("C:\\Users\\rahrai1\\eclipse-workspace\\DemoProjectAPI\\addPlace.json")))
				.when().post("maps/api/place/add/json").then().log().all().statusCode(200).body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		// Add place -> Update Place with new address -> Get Place to validate if the
		// new address is present in response

		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing Json

		String placeID = js.getString("place_id");
		System.out.println(placeID);

		// Update Place
		String newAddress = "Summer Walk, Africa";

		/*************************
		 * Put request to update place id with any details
		 **********************/
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeID + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		/***********************
		 * Get request to get the details of place id
		 ****************************/
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
				.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract()
				.response().asString();
		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, "Summer Walk, Africa");

	}

}
