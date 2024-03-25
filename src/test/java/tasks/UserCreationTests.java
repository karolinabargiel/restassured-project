package tasks;

import org.testng.annotations.Test;
import pojo.user.User;

import static io.restassured.RestAssured.given;

public class UserCreationTests {

    User user = new User();

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        user.setId(448);
        user.setUsername("firstuser");
        user.setFirstName("Krzysztof");
        user.setLastName("Kowalski");
        user.setEmail("krzysztof@test.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(1);

        given().log().all().body(user).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
                .then().log().all().statusCode(200);

        given().log().all()
                .contentType("application/json")
                .when().pathParam("username", user.getUsername())
                .get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
                .then().log().all().statusCode(200);
    }
}
