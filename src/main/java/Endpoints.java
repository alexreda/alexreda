public enum Endpoints{
    BASE_URL("https://app.cosmosid.com"),
    LOGIN_ENDPOINT("/api/v1/login"),
    FILES_ROOT("api/metagenid/v2/files"),
    COUNT_ENDPOINT("api/metagenid/v2/files/count"),
    RUNS("api/metagenid/v1/files/7f4c7326-0a4e-4b56-a8d0-8ce002191672/runs"),
    ANALYSIS("api/metagenid/v1/runs/437ef8e4-b595-4fd8-a2f5-64221831e925/analysis"),
    ARTIFACTS("api/metagenid/v1/runs/437ef8e4-b595-4fd8-a2f5-64221831e925/artifacts");

    private final String label;

    Endpoints(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
