import io.restassured.response.Response;
import java.util.HashMap;

public class LoginAndFiles extends BaseRequest{

    private static Response response;
    private static HashMap <String, Object>queryParams;
    private static HashMap <String, Object>requestBody;
//    public static void main(String[] args) {
    @Test
    public void test1(){

     }
        setBaseUrl(Endpoints.BASE_URL.getLabel());
        // test 1
        queryParams = new HashMap<>(){{
            put("breadcrumbs", "1");
            put("offset", "0");
            put("limit", "1000");
            put("_", "1622700773180");
        }};
        requestBody = new HashMap<>(){{
            put("expiry", "86400");
            put("login_from", "login page");
        }};
        response = login( Endpoints.LOGIN_ENDPOINT.getLabel(),getCredentials(), requestBody);
        System.out.println(getToken(response));
         //test 2
        response = GET(Endpoints.FILES_ROOT.getLabel(), queryParams);

//        System.out.println(response);

        //test 3
/*        queryParams.put("folder_id", "84c966d5-8dce-429d-8f92-44d5e28b1581");
        response = GET(Endpoints.FILES_ROOT.getLabel(), queryParams);
//        System.out.println(response.asString());
        //test 4
        response = GET(Endpoints.COUNT_ENDPOINT.getLabel(), queryParams);
//        System.out.println(response.asString());
        // test 5
        queryParams.put("_", "1622700773181");
        response = GET(Endpoints.RUNS.getLabel(), queryParams);
//        System.out.println(response.asString());
        //test 6
        queryParams.put("_", "1622700773184");
        queryParams.put("filter", "total");
        response = GET(Endpoints.ANALYSIS.getLabel(), queryParams);
//        System.out.println(response.asString());

        //test 7
        queryParams.put("_", "_=1622700773185");
        response = GET(Endpoints.ARTIFACTS.getLabel(), queryParams);
        System.out.println(response.asString());
*/
//    }
}
