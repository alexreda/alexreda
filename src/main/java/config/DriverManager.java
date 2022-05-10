package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.time.Duration;

public class DriverManager {
    public static WebDriver driver;

    public static WebDriver getDriver(DriverType type) {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        } else {
            switch (type) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", DriverPath.CHROME_DRIVER_PATH);
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", DriverPath.GECKO_DRIVER_PATH);
                    driver = new FirefoxDriver();
                    break;
                case OPERA:
                    System.setProperty("webdriver.opera.driver", DriverPath.OPERA_DRIVER_PATH);
                    driver = new OperaDriver();
                    break;
                default:
                    throw new RuntimeException("Invalid browser type: " + type);
            }
//        }
        return driver;
    }
}
