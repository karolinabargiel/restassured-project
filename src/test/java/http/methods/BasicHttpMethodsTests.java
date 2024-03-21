package http.methods;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        String pet = """
                {
                  "id": 123,
                  "category": {
                    "id": 1,
                    "name": "cat"
                  },
                  "name": "Puszak",
                  "photoUrls": [
                    "http://photos.com/cat1.jpg"
                  ],
                  "tags": [
                    {
                      "id": 1,
                      "name": "cat-category"
                    }
                  ],
                  "status": "available"
                }
                """;

        given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);
    }
}
