package http.methods;

import org.testng.annotations.Test;
import pojo.Category;
import pojo.Pet;
import pojo.Tag;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("cat");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("cat-category");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setCategory(category);
        pet.setName("Puszak");
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/cat1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");


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
