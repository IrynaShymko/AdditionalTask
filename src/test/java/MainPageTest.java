import Pages.MainPage;
import Pages.ModalPopUpFormPage;
import Pages.PopUpFormPage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainPageTest extends TestBase {
    private static Logger logger = LoggerFactory.getLogger("MainPageTest.class");


    @Test
    public void shouldShowMessageOnAlert() {
        MainPage mainPage = new MainPage(driver);
        PopUpFormPage popUpFormPage = mainPage.navigateToPopUpFormPage();
        popUpFormPage.clickCreateButton();
        popUpFormPage.switchToLastOpenedWindow();
        ModalPopUpFormPage modalPopUpFormPage = new ModalPopUpFormPage(driver);
        modalPopUpFormPage.fillNameField(System.getProperty("firstName"));
        modalPopUpFormPage.fillDateField();
        modalPopUpFormPage.fillChefField();
        modalPopUpFormPage.fillMealsField(System.getProperty("meal1"), System.getProperty("meal2"), System.getProperty("meal3"));
        modalPopUpFormPage.fillBonusMealField();
        modalPopUpFormPage.confirmModalWindow();
        String actualAlertMessage = modalPopUpFormPage.getTextFromAlert();
        modalPopUpFormPage.acceptAlert();
        assertThat("Alert message is incorrect", actualAlertMessage, equalTo(System.getProperty("expectedMessage")));
    }
}
