import io.restassured.response.Response;

import static io.restassured.RestAssured.request;

public class GetRedirect {
    private static final String BASE_URL = "https://google.com";
    private static Response response;
    private static String newLocation;

    public static void main(String[] args) {

        response = request("GET", BASE_URL);
        if (response.getStatusCode() == 301) {
            newLocation = response.getHeader("Location");
            response = request("GET", newLocation);
        }
        System.out.println(response.body());
    }
}
