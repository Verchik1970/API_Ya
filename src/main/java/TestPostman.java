import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
public class TestPostman {
    @Test

    public void postmanTestGet(){
        RestAssured.when().get("https://postman-echo.com/get").
        then().assertThat().statusCode(200);
        }
}
