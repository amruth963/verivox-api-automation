package com.utils;

import java.util.List;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetCitiesTest {

	@Parameters("postalCode")
	@Test
	public void getCitiesList(String postalCode) {
		Response res = given().when().get("https://service.verivox.de/geo/latestv2/cities/" + postalCode).then()
				.statusCode(200).statusLine("HTTP/1.1 200 OK").extract().response();

		JsonPath jsonPath = res.jsonPath();
		List<String> jsonRes = jsonPath.get("Cities");
		System.out.println("The size of cities is: " + jsonRes.size());
		for (String cities : jsonRes) {
			System.out.println("The city names are: " + cities);
		}
	}

}
