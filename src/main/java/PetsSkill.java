import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.http.ContentType.JSON;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class PetsSkill {
   public static final String endPoint = "https://petfriends.skillfactory.ru/";

    public PetsSkill() throws MalformedURLException {
    }
    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(endPoint)//---> Cтартовая URL
                .setAccept(JSON)//---> Установка Accept
                .build();
    }
    public static ResponseSpecification responseSpecificationScOk() {
        return new ResponseSpecBuilder()
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_OK)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }
}
