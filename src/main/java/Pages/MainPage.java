package Pages;

import Base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(), 'PopupForm')]")
    private WebElement popUpFormLink;

    public PopUpFormPage navigateToPopUpFormPage(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , popUpFormLink);
        clickOnElement(popUpFormLink);
        return new PopUpFormPage(driver);
    }
}
