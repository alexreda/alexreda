import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class LoginAndFiles extends BaseRequest {

    private static Response response;
    private static HashMap<String, Object> queryParams;
    private static HashMap<String, Object> requestBody;

    @BeforeAll
    static void precondition() {
        setBaseUrl(Endpoints.BASE_URL.getLabel());
        enableLogging();
    }

    @Test
    @Order(1)
    void test1() {
        queryParams = new HashMap<>() {{
            put("breadcrumbs", "1");
            put("offset", "0");
            put("limit", "1000");
            put("_", "1622700773180");
        }};
        requestBody = new HashMap<>() {{
            put("expiry", "86400");
            put("login_from", "login page");
        }};
        response = login(Endpoints.LOGIN_ENDPOINT.getLabel(), getCredentials(), requestBody);
        Assertions.assertNotNull(getToken(response));
    }

    @Test
    void test2() {
        response = GET(Endpoints.FILES_ROOT.getLabel(), queryParams);
        Assertions.assertNotNull(response.getBody().jsonPath().get("items"));
    }

    @Test
    void test3() {
        queryParams.put("folder_id", "84c966d5-8dce-429d-8f92-44d5e28b1581");
        response = GET(Endpoints.FILES_ROOT.getLabel(), queryParams);
        Assertions.assertNotNull(response.getBody().jsonPath().get("items"));
    }

    @Test
    void test4() {
        response = GET(Endpoints.COUNT_ENDPOINT.getLabel(), queryParams);
        Assertions.assertSame(response.jsonPath().get("total"), 59);
    }

    @Test
    void test5() {
        queryParams.put("_", "1622700773181");
        response = GET(Endpoints.RUNS.getLabel(), queryParams);
        Assertions.assertTrue(response.asString().contains("runs"));
    }

    @Test
    void test6() {
        queryParams.put("_", "1622700773184");
        queryParams.put("filter", "total");
        response = GET(Endpoints.ANALYSIS.getLabel(), queryParams);
        Assertions.assertTrue(response.asString().contains("analysis"));
    }

    @Test
    void test7() {
        queryParams.put("_", "1622700773185");
        response = GET(Endpoints.ARTIFACTS.getLabel(), queryParams);
        Assertions.assertTrue(response.asString().contains("artifacts"));
    }
}
