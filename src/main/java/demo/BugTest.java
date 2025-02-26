package demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulraiiter.atlassian.net/";

		String createIssueResponse = given().header("Content-Type", "application/json").header("Authorization",
				"Basic cmFodWxyYWlpdGVyQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBlWks3aksyNGVDTU4wUmFTTGRQNUM1M21Vb05jNVYxYTVlem5aUEdMNnlXTEUzQmhWWWVFNDRXVUdhdUJYcWhvVHNSN3dQeTktbVJSRGRLZEJUSk1jdDJWQ1Ryb09xWk00cVdtN1VJQnplSFFUakFUWlR3MmZmS3VvOE91UkVMdC1YMGRIV3ozMjlQa29ONTUxR0p0UDRzYlVFYXdjM1Nuakx3N1FfOXJmaFE9RDQ3QTg3ODA=")
				.body("{\r\n" + "    \"fields\": {\r\n" + "       \"project\":\r\n" + "       {\r\n"
						+ "          \"key\": \"SCRUM\"\r\n" + "       },\r\n"
						+ "       \"summary\": \"Website items are working when user hit API.\",\r\n" + "       \"issuetype\": {\r\n"
						+ "          \"name\": \"Bug\"\r\n" + "       }\r\n" + "   }\r\n" + "}")
				.log().all().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract()
				.response().asString();

		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		System.out.println(issueId);

		// Add attachedment
		String getimagePath=given().pathParam("key", issueId).header("X-Atlassian-Token", "no-check").header("Authorization",
				"Basic cmFodWxyYWlpdGVyQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBlWks3aksyNGVDTU4wUmFTTGRQNUM1M21Vb05jNVYxYTVlem5aUEdMNnlXTEUzQmhWWWVFNDRXVUdhdUJYcWhvVHNSN3dQeTktbVJSRGRLZEJUSk1jdDJWQ1Ryb09xWk00cVdtN1VJQnplSFFUakFUWlR3MmZmS3VvOE91UkVMdC1YMGRIV3ozMjlQa29ONTUxR0p0UDRzYlVFYXdjM1Nuakx3N1FfOXJmaFE9RDQ3QTg3ODA=")
				.multiPart("file", new File("C:\\imageGovind.png")).log().all().post("rest/api/3/issue/{key}/attachments")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(getimagePath);

	}

}
