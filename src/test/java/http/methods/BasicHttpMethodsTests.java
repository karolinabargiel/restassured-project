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

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().log().uri().log().method()
                .pathParam("petId", 1)
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}")
                .then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingPetWhenUpdatePetNameThenPetIsChangedTest() {

        String pet = """
                {
                  "id": 69,
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

        pet = """
                {
                  "id": 69,
                  "category": {
                    "id": 1,
                    "name": "cat"
                  },
                  "name": "Kicia",
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
                .when().put("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingPetIdWhenDeletingPetThenIsDeletedTest() {

        String pet = """
                {
                  "id": 888,
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

        pet = """
                {
                  "id": 888,
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
                .pathParam("petId", 888)
                .when().delete("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}")
                .then().log().all().statusCode(200);
    }
}
