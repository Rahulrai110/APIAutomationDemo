package demo;

import static io.restassured.RestAssured.*;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.restassured.path.json.JsonPath;

public class oAuthTestTwo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// How to get authorization code
//		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
//
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--incognito");
//		WebDriver driver = new ChromeDriver(options);
//
//		driver.get(
//				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("rahulraiiter@gmail.com");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//		Thread.sleep(4000);
//		driver.findElement(By.cssSelector("input[type='password']']")).sendKeys("Sadhna110___");
//		driver.findElement(By.cssSelector("input[type='password']']")).sendKeys(Keys.ENTER);
//		Thread.sleep(4000);
//		String url = driver.getCurrentUrl();
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0ASVgi3I-4KrvjoUlvBdnDRG69_eXP0qCUyWAe6W9JDKmiiEROwmRSXaYWPQZyo77k3SdnA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=1&prompt=none";
		
		String paratialCode = url.split("code=")[1];
		String code = paratialCode.split("&scope")[0];

		// How to get the access token
		String accessTokenResponse = given().urlEncodingEnabled(false)
				.queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");

		// Trigger the main API
		String response = given().queryParam("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);

	}

}
