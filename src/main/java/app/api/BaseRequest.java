package app.api;

import config.Urls;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.PropertyLoader;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class BaseRequest {
    private static String headerKey;
    private static String headerValue;
    private static String jsonPath = "\\src\\main\\resources\\";


    static String formUrl(String path){
        return Urls.getBASE_URL() + path;
    }


    static Response postRequest(String url, HashMap header, HashMap body) {
        headerKey = header.keySet().toArray()[0].toString();
        headerValue = header.get(headerKey).toString();
        return given()
                .header(headerKey, headerValue)
                .body(body)
                .post(url);
    }

    static Response getRequest(String url, HashMap header, HashMap<String, Object> queryParams) {
        headerKey = header.keySet().toArray()[0].toString();
        System.out.println("HEADER_KEY: "+ headerKey);
        System.out.println(header.get(headerKey));
        headerValue = header.get(headerKey).toString();
        return given()
                .header(headerKey, headerValue)
                .queryParams(queryParams)
                .get(url);
    }


    public static void setBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    public static void enableLogging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }

    static String getCredentials() {
        return PropertyLoader.getProperty("CREDENTIALS");
    }

    static ValidatableResponse validateJSONSchema(Response response, String schema){
        File file = new File(System.getProperty("user.dir")+jsonPath+schema);
              return  response.then().assertThat().body(matchesJsonSchema(file));
    }
}
