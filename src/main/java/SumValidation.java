import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourse() {

		JsonPath js = new JsonPath(payload.CoursePrice());

		int count = js.getInt("courses.size()");
		int sum = 0;
		for (int i = 0; i < count; i++) {
			int prices = js.get("courses[" + i + "].price");
			int copiescount = js.get("courses[" + i + "].copies");
			int amount = prices * copiescount;
			System.out.println(amount);
			sum = sum + amount;

		}
		System.out.println(sum);
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, totalAmount);
		
	}
}
