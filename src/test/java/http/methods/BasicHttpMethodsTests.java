package http.methods;

import org.testng.annotations.Test;
import pojo.Category;
import pojo.Pet;
import pojo.Tag;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {

    Category category = new Category();
    Tag tag = new Tag();
    Pet pet = new Pet();

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        category.setId(1);
        category.setName("cat");

        tag.setId(1);
        tag.setName("cat-category");

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

        category.setId(1);
        category.setName("cat");

        tag.setId(1);
        tag.setName("cat-category");

        pet.setId(123);
        pet.setCategory(category);
        pet.setName("Puszak");
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/cat1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);

        pet.setName("Kicia");

        given().log().all().body(pet).contentType("application/json")
                .when().put("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingPetIdWhenDeletingPetThenIsDeletedTest() {

        category.setId(1);
        category.setName("cat");

        tag.setId(1);
        tag.setName("cat-category");

        pet.setId(888);
        pet.setCategory(category);
        pet.setName("Puszak");
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/cat1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);

        given().log().all().body(pet).contentType("application/json")
                .pathParam("petId", pet.getId())
                .when().delete("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}")
                .then().log().all().statusCode(200);
    }
}
