package files;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;

public class DynamicJson {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle)

	{
		RestAssured.baseURI = "http://216.10.245.166";
		String resp = given().header("Content-Type", "application/json").body(payload.Addbook(isbn, aisle)).when()
				.post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(resp); // for parsing Json
		String id = js.get("ID");
		System.out.println(id);
		// deleteBOok

	}

	@DataProvider(name = "BooksData")
	public Object[][] getData()

	{

//array=collection of elements
//multidimensional array= collection of arrays
		return new Object[][] { { "dfsfzdf", "342452" }, { "dsfsrfgs", "5645634" } };

	}

}
