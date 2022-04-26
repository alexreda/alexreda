package config;

import lombok.Getter;

public class Urls {
  @Getter
  private static final String BASE_URL = "https://app.cosmosid.com/";
  @Getter
  private static final String LOGIN_ENDPOINT = "app/v1/login";
  @Getter
  private static final String ROOT_FOLDER = "app/metagenid/v2/files";
  @Getter
  private static final String COUNT_ENDPOINT = "app/metagenid/v2/files/count";
}
