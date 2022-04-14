import Pages.MainPage;
import Pages.ModalPopUpPage;
import Pages.PopUpFormPage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.readers.YMLreader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModalPopUpPageTest extends TestBase {
    private static Logger logger = LoggerFactory.getLogger("ModalPopUpPageTest.class");

    @Test
    public void shouldShowMessageOnAlert() {
        MainPage mainPage = new MainPage(driver);
        PopUpFormPage popUpFormPage = mainPage.navigateToPopUpFormPage();
        popUpFormPage.clickCreateButton()
                .switchToLastOpenedWindow();
        ModalPopUpPage modalPopUpFormPage = new ModalPopUpPage(driver);
        modalPopUpFormPage.fillNameField(System.getProperty("firstName"))
                .fillDateField()
                .fillChefField()
                .fillMealsField(System.getProperty("meal1"), System.getProperty("meal2"), System.getProperty("meal3"))
                .fillBonusMealField()
                .confirmModalWindow();
        String actualAlertMessage = modalPopUpFormPage.getTextFromAlert();
        logger.info("<<<<<<<<<< Actual alert message is: " + actualAlertMessage);
        logger.info("<<<<<<<<<< Expected alert message is: " + System.getProperty("expectedMessage"));
        modalPopUpFormPage.acceptAlert();
        assertThat("Alert message is incorrect", actualAlertMessage, equalTo(System.getProperty("expectedMessage")));
    }
}
