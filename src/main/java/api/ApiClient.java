package api;

import config.Urls;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.HashMap;

public class ApiClient extends BaseRequest {
    @Getter
    private static String token;
    private static Response response;
    private static String url;
    private static HashMap<String, Object> header;
    private static HashMap<String, Object> body;
    private static HashMap<String, Object> queryParams;

    public static Response login() {
        url = formUrl(Urls.getLOGIN_ENDPOINT());
        header = new HashMap<String, Object>(){{
            put("Authorization", getCredentials());
        }};
        body = new HashMap<String, Object>() {{
            put("expiry", "86400");
            put("login_from", "login page");
        }};
        response = postRequest(url, header, body);
        token = response.getBody().jsonPath().get("token");
        return response;
    }

    public static Response getRootFolder(){
        url = formUrl(Urls.getROOT_FOLDER());
        header = new HashMap<String, Object>(){{
            put("x-token", token);
        }};
        queryParams = new HashMap<String, Object>() {{
            put("breadcrumbs", "1");
            put("offset", "0");
            put("limit", "1000");
            put("_", "1622700773180");
        }};
        return BaseRequest.getRequest(url, header, queryParams);
    }
    public static Response getSpecificFolder(final String folderId){
        url = formUrl(Urls.getROOT_FOLDER());
        header = new HashMap<String, Object>(){{
            put("x-token", token);
        }};
        queryParams = new HashMap<String, Object>() {{
            put("breadcrumbs", "1");
            put("offset", "0");
            put("limit", "1000");
            put("_", "1622700773180");
            put("folder_id", folderId);
        }};
        return BaseRequest.getRequest(url, header, queryParams);
    }

    public static Response countFilesInFolder(final String folderId) {
        url = formUrl(Urls.getCOUNT_ENDPOINT());
        header = new HashMap<String, Object>(){{
            put("x-token", token);
        }};
        queryParams = new HashMap<String, Object>() {{
            put("folder_id", folderId);
        }};
        return BaseRequest.getRequest(url, header, queryParams);
    }
}
