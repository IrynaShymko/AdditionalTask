package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopUpFormPage extends BasePage {
    private SideBarPage sideBarPage;
    private static Logger logger = LoggerFactory.getLogger("PopUpFormPage.class");

    public PopUpFormPage(WebDriver driver) {
        super(driver);
        this.sideBarPage=new SideBarPage(driver);
        logger.info("########## PopUpFormPage is created");
    }
    @FindBy(xpath = "//button[contains(text(), 'Create')]")
    private WebElement createButton;

    public PopUpFormPage clickCreateButton(){
        clickOnElement(createButton);
        return this;
    }
}
