package utils;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class JsonSchemaValidator {
    private static final String SCHEMA_PATH =
            File.separator + "src" +
                    File.separator + "main" +
                    File.separator + "resources" +
                    File.separator;

    public static ValidatableResponse validateJSONSchema(Response response, String schema) {
        return response.then().assertThat().body(matchesJsonSchema(getSchemaFile(schema)));
    }

    private static File getSchemaFile(String schema) {

        File file = new File(System.getProperty("user.dir") + SCHEMA_PATH + schema);
        return file;
    }
}
