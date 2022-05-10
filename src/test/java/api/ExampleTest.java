package api;

import app.api.ApiClient;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.JsonSchemaValidator;
import utils.PropertyLoader;


@Log
public class ExampleTest {

    private static Response response;

    @BeforeAll
    public static void precondition() {
        ApiClient.enableLogging("error");
        ApiClient.login();
    }

    @Test
    @DisplayName("Test if token is received upon login attempt")
    public void testLogin() {
       Assertions.assertNotNull(ApiClient.getToken(), "Verify token was received");
    }

    @Test
    @DisplayName("Test if root folder contains files")
    public void testGetFilesFromRoot() {
        response = ApiClient.getRootFolder();
        Assertions.assertNotNull(response.getBody().jsonPath().get("items"),
                "Verify response body contains items");
    }
    @Test
    @DisplayName("Test if correct response is received  upon specific folder request")
    public void testGetSpecificFolder() {
        response = ApiClient.getSpecificFolder("84c966d5-8dce-429d-8f92-44d5e28b1581");
        Assertions.assertEquals("Example_Datasets", response.getBody().jsonPath().get("name"),
                "Verify response body contains [Example_Datasets] flag");
    }
    @Test
    @DisplayName("Test if response body contains total file count information")
    public void testCountFilesInFolder() {
        response = ApiClient.countFilesInFolder("84c966d5-8dce-429d-8f92-44d5e28b1581");
        Assertions.assertSame(59, response.jsonPath().get("total"),
                "Verify total file count is 59");
    }
    @Test
    @DisplayName("Test if response body matches the defined schema")
    public void testGetArtifactsByRunId() {
        response = ApiClient.getArtifactsByRunId("437ef8e4-b595-4fd8-a2f5-64221831e925");
        JsonSchemaValidator.validateJSONSchema(response, PropertyLoader.getProperty("ARTIFACTS"));
    }
}
