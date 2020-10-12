package br.com.helpdev.jynx.entrypoint.controller.v1;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LabelDetectorControllerTest {

    @Test
    void testHelloEndpoint() {
        given()
                .when().get(LabelDetectorController.ROOT_PATH)
                .then()
                .statusCode(200)
                .body(is("hello"));
    }

}