import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(payload.CoursePrice());

		// Print no of courses count return from API
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		// Print Title of the first course
		String titleName = js.getString("courses[0].title");
		System.out.println(titleName);

		// Print All course titles and their respective Prices
		for (int i = 0; i < count; i++) {
			String coursetitles = js.get("courses[" + i + "].title");
			System.out.println(js.get("courses[" + i + "].price").toString());
			System.out.println(coursetitles);
			// System.out.println(prices);
		}

		// Print no of copies sold by RPA Course
		System.out.println("Print no of copies sold by RPA Course");

		for (int i = 0; i < count; i++) {
			String coursetitles = js.get("courses[" + i + "].title");
			if (coursetitles.equalsIgnoreCase("RPA")) {
				int copiescount = js.get("courses[" + i + "].copies");
				System.out.println(copiescount);
				break;
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		
		
	}
}
