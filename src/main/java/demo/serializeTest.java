package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName("Rahul Rai");
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setType(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		RequestSpecification res = given().spec(req).body(p);

		Response response = res.when().post("maps/api/place/add/json").then().spec(resspec).extract().response();
		String responseString = response.asString();
		System.out.println(responseString);

//		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
//				.body(p).when().post("maps/api/place/add/json").then().log().all().statusCode(200)
//				.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		// JsonPath js = new JsonPath(response); // for parsing Json

		// String placeID = js.getString("place_id");
		// System.out.println(placeID);

	}

}
