package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ModalPopUpPage extends BasePage {
    private Actions actions;
    private static Logger logger = LoggerFactory.getLogger("ModalPopUpFormPage.class");

    public ModalPopUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='createDinnerName-awed']")
    private WebElement nameField;

    @FindBy(xpath = "//span[@class='awe-icon awe-icon-datepicker']")
    private WebElement calendarIcon;

    @FindBy(xpath = "//td[contains(@class, 'o-day o-mday')]")
    private List<WebElement> daysOfMonth;

    @FindBy(xpath = "//input[@name='Chef']/following-sibling::button")
    private WebElement chefFieldOpenButton;

    @FindBy(xpath = "//div[@class='awe-list awe-srlcont']")
    private WebElement allShefsElement;

    @FindBy(xpath = "//li[@class='awe-li']")
    private List<WebElement> listOfShefs;

    @FindBy(xpath = "//div[@class='awe-multilookup-field awe-field']//button")
    private WebElement mealsFieldOpenButtonForAddFirstProduct;

    @FindBy(xpath = "//div[starts-with(@class,'awe-multilookup')]//button")
    private WebElement mealsFieldOpenButtonForAddAnotherProduct;

    @FindBy(xpath = "//div[@data-i='createDinnerMeals-awepw']//input[@name='search']")
    private WebElement searchMealField;

    @FindBy(xpath = "//div[@id='createDinnerMeals-awepw']//li[@class='awe-li']//i")
    private WebElement mealsFoundIcon;

    @FindBy(xpath = "//div[@data-i='createDinnerMeals-awepw']//button[@class='awe-btn awe-okbtn o-pbtn']")
    private WebElement okConfirmationOfMealsButton;

    @FindBy(xpath = "//button[@id='createDinnerBonusMealId-awed']//i")
    private WebElement iconForOpenBonusMealList;

    @FindBy(xpath = "//div[@data-i='createDinnerBonusMealId']//li")
    private List<WebElement> bonusMealList;

    @FindBy(xpath = "//div[@data-i='createDinnerBonusMealId']//input[@placeholder=\"Search...\"]")
    private WebElement searchFieldBonusMeal;

    @FindBy(xpath = "//div[@data-i='createDinner']//button[@class='awe-btn awe-okbtn o-pbtn']")
    private WebElement confirmModalWindowButton;

    public void fillNameField(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameField));
        sendKeys(nameField, name);
    }

    public void clickCalendarIcon() {
        clickOnElement(calendarIcon);
    }

    public void chooseRandomDayOnCalendar() {
        chooseRandomValueFromList(daysOfMonth);
    }

    public void fillDateField() {
        clickCalendarIcon();
        chooseRandomDayOnCalendar();
    }

    public void clickChefFieldOpenButton() {
        clickOnElement(chefFieldOpenButton);
    }

    public void chooseChef() {
        wait.until(ExpectedConditions.visibilityOf(allShefsElement));
        int index = new Random().nextInt(listOfShefs.size());
        actions.moveToElement(listOfShefs.get(index)).perform();
        actions.moveToElement(listOfShefs.get(index)).doubleClick().perform();
    }

    public void fillChefField() {
        clickChefFieldOpenButton();
        chooseChef();
    }

    public void chooseFirstProduct(String meal1) throws InterruptedException {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchMealField));
            sendKeys(searchMealField, meal1);
//        actions = new Actions(driver);
            clickOnElement(nameField);
            Thread.sleep(2000);
            hoverAndClick(mealsFoundIcon);
            clickOnElement(okConfirmationOfMealsButton);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void chooseAnotherProduct(String meal) {
        wait.until(ExpectedConditions.elementToBeClickable(searchMealField));
        clearFieldAndSendKeys(searchMealField, meal);
        clickOnElement(nameField);
        try {
            hoverAndClick(mealsFieldOpenButtonForAddAnotherProduct);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            hoverAndClick(mealsFoundIcon);
            clickOnElement(okConfirmationOfMealsButton);
        }
    }

    public void fillMealsField(String meal1, String meal2, String meal3) {
        try {
            clickOnElement(mealsFieldOpenButtonForAddFirstProduct);
            chooseFirstProduct(meal1);
            Thread.sleep(1000);

            hoverAndClick(mealsFieldOpenButtonForAddAnotherProduct);
            chooseAnotherProduct(meal2);
            Thread.sleep(1000);

            hoverAndClick(mealsFieldOpenButtonForAddAnotherProduct);
            chooseAnotherProduct(meal3);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void openBonusMealList() {
        clickOnElement(iconForOpenBonusMealList);
    }

    public void selectBonusMeal() {
        chooseRandomValueFromList(bonusMealList);
    }

    public void fillBonusMealField() {
        openBonusMealList();
        selectBonusMeal();
    }

    public void confirmModalWindow() {
        clickOnElement(confirmModalWindowButton);
    }


}
