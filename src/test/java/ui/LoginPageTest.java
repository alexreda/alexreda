package ui;

import app.ui.pageObject.LoginPage;
import config.DriverManager;
import config.DriverType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LoginPageTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static final String EMAIL = "zhaba@gmail.com";
    private static final String PASSWORD = "test123";

    @BeforeAll
    public static void invokeBrowser(){
    driver = DriverManager.getDriver(DriverType.CHROME);
    loginPage = new LoginPage(driver);
}
    @Test
    public void testFailedLogin(){
        driver.get(loginPage.getLoginPageUrl());
        loginPage.login(EMAIL, PASSWORD);
        Assertions.assertEquals(loginPage.getIncorrectPasswordMessage().getText(),loginPage.getExpectedFailMessage());
}
}
