package http.methods;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCreationTests {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {
        String user = """
                {
                  "id": 445,
                  "username": "firstuser",
                  "firstName": "Krzysztof",
                  "lastName": "Kowalski",
                  "email": "krzysztof@test.com",
                  "password": "password",
                  "phone": "+123456789",
                  "userStatus": 1
                }
                """;

        given().log().all().body(user).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
                .then().log().all().statusCode(200);

        given().log().all()
                .contentType("application/json")
                .when().pathParam("username", "firstuser")
                .get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
                .then().log().all().statusCode(200);
    }
}
