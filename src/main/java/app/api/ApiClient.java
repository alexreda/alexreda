package app.api;

import config.Urls;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.HashMap;

import static utils.Helper.formUrl;

public class ApiClient extends BaseRequest {
    @Getter
    @Setter
    private static String token;
    private static Response response;
    private static String url;
    private static HashMap<String, Object> header;
    private static HashMap<String, Object> body;
    private static HashMap<String, Object> queryParams;


    public static Response login() {
        url = formUrl(Urls.getLOGIN_ENDPOINT());
        header = new HashMap<>() {{
            put("Authorization", getCredentials());
        }};
        body = new HashMap<>() {{
            put("expiry", getExpiryTime());
            put("login_from", getLoginEntryPoint());
        }};
        response = postRequest(url, header, body);
        setToken(response.getBody().jsonPath().get("token"));
        return response;
    }

    public static HashMap<String, Object> formAuthHeader() {
        return new HashMap<>() {{
            put("x-token", getToken());
        }};
    }

    public static HashMap<String, Object> setQueryParams(String... params) {
        queryParams = new HashMap<>();
        switch (params.length) {
            case 1:
                queryParams.put("breadcrumbs", params[0]);
                break;
            case 2:
                queryParams.put("offset", params[1]);
                break;
            case 3:
                queryParams.put("limit", params[2]);
                break;
            default:
                queryParams.put("breadcrumbs", "1");
                queryParams.put("offset", "0");
                queryParams.put("limit", "1000");
        }
        return queryParams;
    }

    public static Response getRootFolder(String... params) {
        url = formUrl(Urls.getROOT_FOLDER());
        header = formAuthHeader();
        queryParams = setQueryParams(params);
        return BaseRequest.getRequest(url, header, queryParams);
    }

    public static Response getSpecificFolder(final String folderId, String... params) {
        url = formUrl(Urls.getROOT_FOLDER());
        header = formAuthHeader();
        queryParams = setQueryParams(params);
        queryParams.put("folder_id", folderId);
        return BaseRequest.getRequest(url, header, queryParams);
    }

    public static Response countFilesInFolder(final String folderId) {
        url = formUrl(Urls.getCOUNT_ENDPOINT());
        header = formAuthHeader();
        queryParams = new HashMap<>() {{
            put("folder_id", folderId);
        }};
        return BaseRequest.getRequest(url, header, queryParams);
    }

    public static Response getArtifactsByRunId(final String runId) {
        url = formUrl(MessageFormat.format(Urls.getARTIFACTS(), runId));
        header = formAuthHeader();
        queryParams = new HashMap<>();
        return BaseRequest.getRequest(url, header, queryParams);
    }


}
