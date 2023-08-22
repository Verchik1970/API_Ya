import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestPostman {



    @Test
    @DisplayName("проверка кода 200")

    public void askillGetTest() {
        RestAssured.given().spec(PetsSkill.requestSpecification()).get("/login")
                .then().statusCode(200);

    }

    static String authKey;
    static String email = "ver@mail.ee";
    static String password = "121970";
    static String petId;
    static String petIdNew;


    @BeforeAll
    @DisplayName("Получение авторизационного ключа")
    public static void skillApiKeyGetTest() {

        Response response = given().header("email", email).header("password", password)
                .when().spec(PetsSkill.requestSpecification()).get("/api/key");
        String responseStr = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(responseStr);
        authKey = jsonPath.get("key");
        System.out.println(authKey);
    }


    @Test
    @DisplayName("Создание питомца")
    public void createPetTest() {
        baseURI = "https://petfriends.skillfactory.ru/";

        String response = given()
                .header("auth_key", authKey)
                .contentType("application/json")
                .body("{ \"name\": \"TOTO\", \"animal_type\": \"dog\", \"age\": 14 }")
                .when()
                .post("/api/create_pet_simple")
                .then()
                .statusCode(200)
                .extract().response().asString()
                ;
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        petId = jsonPath.get("id");

        System.out.println(petId);

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
    @Test
    @DisplayName("Обновление информации о питомце")
    public void updatePetInfoTest() {
        baseURI = "https://petfriends.skillfactory.ru/";

        String response1 = given()
                .header("auth_key", authKey)
                .contentType("application/json")
                .body("{ \"name\": \"AVVA\", \"animal_type\": \"dog\", \"age\": 6 }")
                .when()
                .put("/api/pets/" + petId)
                .then()
                .statusCode(200)
                .extract().response().asString()
                ;
        System.out.println(response1);
        JsonPath jsonPath1 = new JsonPath(response1);
        petIdNew = jsonPath1.get("id");

        System.out.println(petIdNew);

    }

    @Test
    @DisplayName("сравнение petId")
    public void ycomparePetId(){
       Assertions.assertEquals(petId, petIdNew);
    }
    @Test
    @DisplayName("delete")
    public void zdeletePetTest() {
        baseURI = "https://petfriends.skillfactory.ru/";
        given()
                .header("auth_key", authKey)
                .contentType("application/json")
                .when()
                .delete("api/pets/" + petId)
                .then()
                .statusCode(200)
                .log().body();

    }
}











