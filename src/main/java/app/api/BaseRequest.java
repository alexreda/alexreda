package app.api;

import config.Urls;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import utils.PropertyLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BaseRequest {
//
//    static String formUrl(String path) {
//        return Urls.getBASE_URL() + path;
//    }

    static Response postRequest(String url, HashMap<String, Object> headers, HashMap body) {

        return given()
                .headers(headers)
                .body(body)
                .post(url);
    }

    static Response getRequest(String url, HashMap<String, Object> headers, HashMap<String, Object> queryParams) {

        return given()
                .headers(headers)
                .queryParams(queryParams)
                .get(url);
    }

    public static void setBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    static String getCredentials() {
        return PropertyLoader.getProperty("CREDENTIALS");
    }

    static String getExpiryTime() {
        return PropertyLoader.getProperty("LOGIN_EXPIRATION");
    }

    static String getLoginEntryPoint() {
        return PropertyLoader.getProperty("LOGIN_ENTRY_POINT");
    }

    public static void enableLogging(String scope) {
        switch (scope){
            case "request":
                RestAssured.filters(new RequestLoggingFilter(), new ErrorLoggingFilter());
            case "response":
                RestAssured.filters(new ResponseLoggingFilter(), new ErrorLoggingFilter());
            case "full":
                RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
            default:
                RestAssured.filters(new ErrorLoggingFilter());
        }
    }
}
