package config;

import java.io.File;

public class DriverPath {
    public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir")
            + File.separator +"src"
            + File.separator +"main"
            + File.separator +"resources"
            + File.separator +"chromedriver.exe";
    public static final String GECKO_DRIVER_PATH = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator +"main"
            + File.separator +"resources"
            + File.separator +"geckodriver.exe";
    public static final String OPERA_DRIVER_PATH = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator +"main"
            + File.separator +"resources"
            + File.separator +"operadriver.exe";
}
