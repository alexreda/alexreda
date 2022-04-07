import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequest {

    private static final String BASE_URL = "https://google.com";

    public static void main(String[] args) {
        //assertion & printing status line
        String responseStatus = RestAssured
                .given().get(BASE_URL)
                .then().assertThat().statusCode(200)
                .extract().response().getStatusLine();
        System.out.println(responseStatus);
        //printing response components
        Response response = RestAssured.request("GET", BASE_URL);
        System.out.println(response.contentType()+'\n' + response.getStatusCode()+ '\n' + response.headers());
    }
}