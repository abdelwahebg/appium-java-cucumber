package bukalapak.api;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class RestAssuredTest {

    @Test
    public void makeSureThatBukalapakIsUp() {
        given()
                .when()
                .get("https://www.bukalapak.com")
                .then()
                .statusCode(200);
    }

    @Test
    public void openAgenBukalapakPage() {
        given()
                .when()
                .get("http://agen.staging63.vm/agenlite/manifest.json")
                .then()
                .statusCode(200)
                .body("name", equalTo("Agen Bukalapak Lite"));
    }

}
