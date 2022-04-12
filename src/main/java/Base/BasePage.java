package Base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import properties.BrowserEnvironment;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(BrowserEnvironment.getWebElementTimeOut()));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnElement(WebElement webElement) {
        webElement.click();
    }

    public void hoverAndDoubleClick(WebElement webElement) {
        actions = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        actions.moveToElement(webElement).perform();
        actions.moveToElement(webElement).doubleClick().perform();
    }

    public String getTextFromAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void clearFieldAndSendKeys(WebElement webElement, String value) {
        webElement.clear();
        webElement.sendKeys(value);
    }

    public void sendKeys(WebElement webElement, String value) {
        webElement.sendKeys(value);
    }

    public void chooseRandomValueFromList(List<WebElement> webElements) {
        int index = new Random().nextInt(webElements.size());
        webElements.get(index).click();
    }
    public void switchToLastOpenedWindow(){
        for(String windowHandle: driver.getWindowHandles()){
            driver.switchTo().window(windowHandle);
        }
    }
}
