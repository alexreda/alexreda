package config;

import lombok.Getter;

public class Urls {
  @Getter
  private static final String BASE_URL = "https://app.cosmosid.com/";
  @Getter
  private static final String LOGIN_ENDPOINT = "api/v1/login";
  @Getter
  private static final String ROOT_FOLDER = "api/metagenid/v2/files";
  @Getter
  private static final String COUNT_ENDPOINT = "api/metagenid/v2/files/count";
  @Getter
  private static final String RUNS = "api/metagenid/v1/files/{0}/runs";
  @Getter
  private static final String ANALYSIS = "api/metagenid/v1/runs/{0}/analysis";
  @Getter
  private static final String ARTIFACTS = "api/metagenid/v1/runs/{0}/artifacts";
  @Getter
  private static final String LOGIN_URL = "login";
}
