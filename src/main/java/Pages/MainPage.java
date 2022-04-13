package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage extends BasePage {
    private SideBarPage sideBarPage;
    private static Logger logger = LoggerFactory.getLogger("MainPage.class");
    public MainPage(WebDriver driver) {
        super(driver);
        this.sideBarPage=new SideBarPage(driver);
        logger.info("########## MainPage is created");
    }

    public PopUpFormPage navigateToPopUpFormPage(){
        return sideBarPage.navigateToPopUpFormPageFromSideBar();
    }
}
