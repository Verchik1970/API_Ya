import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestPostman {
    @Test
    @DisplayName("проверка кода 200")

    public void postmanTestGet() {
        RestAssured.when().get("https://postman-echo.com/get?foo1=bar1&foo2=bar2").
                then().assertThat().statusCode(200);

    }

    @Test
    @DisplayName("Проверка POST")
    public void postmanTestPost() {
        RestAssured.when().post("https://api-101.glitch.me/customers").
                then().assertThat().body("userId", Matchers.is("userId"));
    }

}

