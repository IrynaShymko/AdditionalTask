package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PopUpFormPage extends BasePage {


    public PopUpFormPage(WebDriver driver) {
       super(driver);
    }
    @FindBy(xpath = "//button[contains(text(), 'Create')]")
    private WebElement createButton;

    public void clickCreateButton(){
        createButton.click();
    }
    public void switchToLastOpenedWindow(){
        for(String windowHandle: driver.getWindowHandles()){
            driver.switchTo().window(windowHandle);
        }
    }
}
