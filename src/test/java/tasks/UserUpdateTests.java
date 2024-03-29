package tasks;

import base.TestBase;
import org.testng.annotations.Test;
import pojo.user.User;

import static io.restassured.RestAssured.given;

public class UserUpdateTests extends TestBase {
    User user = new User();

    @Test
    public void givenCorrectUserDataWhenFirstNameLastNameAreUpdatedTest() {
        user.setId(590);
        user.setUsername("seconduser");
        user.setFirstName("Krzysztof");
        user.setLastName("Kowalski");
        user.setEmail("krzysztof@test.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(1);

        given().log().all().body(user).contentType("application/json")
                .when().post("user")
                .then().log().all().statusCode(200);

        user.setFirstName("Damian");
        user.setLastName("Gajos");

        given().log().all().body(user)
                .contentType("application/json")
                .when().pathParam("username", user.getUsername())
                .put("user/{username}")
                .then().log().all().statusCode(200);

        given().log().all()
                .contentType("application/json")
                .when().pathParam("username", user.getUsername())
                .get("user/{username}")
                .then().log().all().statusCode(200);
    }
}
