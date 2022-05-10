package app.ui.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePopupPage extends BasePage {
    public BasePopupPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//button='contains(text(),'Do not show again'])")
    private WebElement denyBtn;
    @FindBy(xpath = "//button[contains(text(), 'Ok')]")
    private WebElement acceptBtn;

    public void accept(){
        acceptBtn.click();
    }
    public void deny(){
        denyBtn.click();
    }
}
