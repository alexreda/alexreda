package utils;

import config.Urls;

public class Helper {
    public static String formUrl(String path) {
        return Urls.getBASE_URL() + path;
    }
}
