import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.PropertyLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class BaseRequest {
    private static String token;
    private static String configPath = "\\src\\main\\resources\\config.properties";

    static Response login(String path, String encodedCreds, HashMap body) {
        return given()
                .basePath(path)
                .header("Authorization", encodedCreds)
                .body(body)
                .post();
    }

    static Response GET(String path, HashMap queryParams) {
        return given()
                .basePath(path)
                .header("x-token", token)
                .queryParams(queryParams)
                .get();
    }

    static String getToken(Response response) {
        token = response.jsonPath().get("token");
        return token;
    }

    static void setBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    static void enableLogging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }

    static String getCredentials() {
        return PropertyLoader.getProperty("CREDENTIALS");
    }
    static ValidatableResponse validateJSONSchema(Response response, String schema){
              return  response.then().assertThat().body(matchesJsonSchemaInClasspath(schema));
    }
}
