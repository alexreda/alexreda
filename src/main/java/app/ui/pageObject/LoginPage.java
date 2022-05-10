package app.ui.pageObject;

import config.Urls;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.Helper.formUrl;
@Getter
public class LoginPage extends BasePage {
    private String loginPageUrl = formUrl(Urls.getLOGIN_URL());
    private String expectedFailMessage = "Password is not correct";

    public LoginPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//h1[contains(text(), 'Sign in')]")
    private WebElement title;
    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy (id = "signInButton")
    private WebElement signInBtn;
    @FindBy (id = "message-id")
    private WebElement incorrectPasswordMessage;

    public void login(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInBtn.click();
    }
}
