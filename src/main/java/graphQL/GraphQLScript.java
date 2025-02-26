package graphQL;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Query
		int characterId = 12629;
		String response = given().log().all().header("Content-Type", "application/json").body(
				"{\"query\":\"query($characterId: Int!,$episodeId: Int!)\\n{\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: 18489)\\n  {\\n    name\\n    dimension\\n\\n  }\\n  episode(episodeId:$episodeId)\\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n\\n  characters(filters:{ name:\\\"Rahul Rai\\\"})\\n  {\\n    info\\n    {\\n      count\\n    }\\n    result\\n    {\\n      name\\n      type\\n    }\\n  }\\n  \\n  episodes(filters:{episode: \\\"OnePiece\\\"})\\n  {\\n    result\\n    {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":"
						+ characterId + ",\"episodeId\":13107}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String characterName = js.getString("data.character.name");
		Assert.assertEquals(characterName, "Rahul Rai");

		// Mutations
		String newCharacter = "RahulR";
		String mutationResponse = given().log().all().header("Content-Type", "application/json").body(
				"{\"query\":\"mutation($locationName:String!,$characterName:String!,$episodeName:String!)\\n{\\n  createLocation(location:{name:$locationName,type :\\\"SouthZone\\\",dimension: \\\"234\\\"})\\n  {\\n    id\\n  }\\n  \\n  createCharacter(character:{name:$characterName,type:\\\"Macho\\\",status:\\\"dead\\\",species:\\\"fantasy\\\",gender:\\\"male\\\",image:\\\"png\\\",originId:151,locationId:151})\\n  {\\n    id \\n  }\\n  \\n  createEpisode(episode:{name:$episodeName,air_date:\\\"1950 June\\\",episode:\\\"Netflix\\\"})\\n  {\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[18483,18482])\\n  {\\n    locationsDeleted\\n  }\\n  \\n}\\n\",\"variables\":{\"locationName\":\"Kalyan Nagar\",\"characterName\":\""
						+ newCharacter + "\",\"episodeName\":\"OnePiece\"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

		System.out.println(mutationResponse);
		JsonPath jsp = new JsonPath(mutationResponse);

	}

}
