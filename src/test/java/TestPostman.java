import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestPostman {
    @Test
    @DisplayName("проверка кода 200")

    public void skillGetTest() {
       RestAssured.given().spec(PetsSkill.requestSpecification()).get("/login")
               .then().statusCode(200);

    }

    @Test
    public void skillApiKeyGetTest() {

        Response response =  given().header("email","ver@mail.ee").header("password", "121970")
                .when().spec(PetsSkill.requestSpecification()).get("/api/key");

        String getBody = response.getBody().asString();

        System.out.println(getBody);
    }






    }
   /* @Test
    @DisplayName("Проверка POST")
    public void postmanTestPost() {
        RestAssured.when().post("https://api-101.glitch.me//customers").
                then().assertThat().a;
    }*/


