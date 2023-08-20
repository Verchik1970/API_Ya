import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestPostman {


    @Test
    @DisplayName("проверка кода 200")

    public void skillGetTest() {
        RestAssured.given().spec(PetsSkill.requestSpecification()).get("/login")
                .then().statusCode(200);

    }

    static String authKey;

    @BeforeAll
    @DisplayName("Получение авторизационного ключа")
    public static void skillApiKeyGetTest() {

        Response response = given().header("email", "ver@mail.ee").header("password", "121970")
                .when().spec(PetsSkill.requestSpecification()).get("/api/key");
        String responseStr = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(responseStr);
        authKey = jsonPath.get("key");

    }


    @Test
    @DisplayName("Создание питомца")
    public void createPetTest() {
        baseURI = "https://petfriends.skillfactory.ru/";

        given()
                .header("auth_key", authKey)
                .contentType("application/json")
                .body("{ \"name\": \"Lucky\", \"animal_type\": \"dog\", \"age\": 14 }")
                .when()
                .post("/api/create_pet_simple")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    @DisplayName("Получение списка моих питомцев")
    public void getPetListTest() {
        baseURI = "https://petfriends.skillfactory.ru/";

        given()
                .header("auth_key", authKey)
                .contentType("application/json")
                .param("filter", "my_pets")
                .when()
                .get("/api/pets")
                .then()
                .statusCode(200)
                .log().body();


    }
}











