package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopUpFormPage {
    private WebDriver driver;

    public PopUpFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
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
