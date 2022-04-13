package Pages;

import Base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SideBarPage extends BasePage {
    private static Logger logger = LoggerFactory.getLogger("SideBarPage.class");

    public SideBarPage(WebDriver driver) {
        super(driver);
        logger.info("########## SideBarPage is created");
    }

    @FindBy(xpath = "//a[contains(text(), 'PopupForm')]")
    private WebElement popUpFormLink;

    public PopUpFormPage navigateToPopUpFormPageFromSideBar(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , popUpFormLink);
        clickOnElement(popUpFormLink);
        return new PopUpFormPage(driver);
    }
}
