package demo;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojo.GetCourse;
import pojo.WebAutomation;
import pojo.api;

public class oAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Automation API using oAuth 1.0
		String[] courseTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };

		String Respone = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		JsonPath js = new JsonPath(Respone);
		String accessToken = js.getString("access_token");
		System.out.println(accessToken);

		// Get Course Details
		GetCourse gc = given().queryParam("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());

		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

		List<api> apicourses = gc.getCourses().getApi();
		for (int i = 0; i < apicourses.size(); i++) {
			if (apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {

				System.out.println(apicourses.get(i).getPrice());
			}
		}

		// get the course names of the webautomation
		ArrayList<String> a = new ArrayList();
		List<WebAutomation> wa = gc.getCourses().getWebAutomation();

		for (int j = 0; j < wa.size(); j++) {
			a.add(wa.get(j).getCourseTitle());

		}
		List<String> expectedList = Arrays.asList(courseTitles);
		Assert.assertTrue(a.equals(expectedList));

	}

}
