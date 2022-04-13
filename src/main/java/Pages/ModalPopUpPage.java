package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class ModalPopUpPage extends BasePage {

    private static Logger logger = LoggerFactory.getLogger("ModalPopUpFormPage.class");

    public ModalPopUpPage(WebDriver driver) {
        super(driver);
        logger.info("########## ModalPopUpPage is created");
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
    private WebElement allChefsElement;

    @FindBy(xpath = "//li[@class='awe-li']")
    private List<WebElement> listOfChefs;

    @FindBy(xpath = "//input[@id='createDinnerMeals']//following-sibling::button")
    private WebElement mealsFieldOpenButtonForAddProduct;

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

    @FindBy(xpath = "//span[@id='createDinnerChef-error']")
    private WebElement validationChefError;

    public ModalPopUpPage fillNameField(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameField));
        sendKeys(nameField, name);
        return this;
    }

    public void clickCalendarIcon() {
        clickOnElement(calendarIcon);
    }

    public void chooseRandomDayOnCalendar() {
        chooseRandomValueFromList(daysOfMonth);
    }

    public ModalPopUpPage fillDateField() {
        clickCalendarIcon();
        chooseRandomDayOnCalendar();
        logger.info("<<<<<<<<<< Date field is filled");
        return this;
    }

    public void clickChefFieldOpenButton() {
        clickOnElement(chefFieldOpenButton);
    }

    public void chooseChef() {
        wait.until(ExpectedConditions.visibilityOf(allChefsElement));
        int index = new Random().nextInt(listOfChefs.size());
        hoverAndDoubleClick(listOfChefs.get(index));
    }

    public ModalPopUpPage fillChefField() {
        clickChefFieldOpenButton();
        chooseChef();
        logger.info("<<<<<<<<<< Chef field is filled");
        return this;
    }

    public void chooseFirstProduct(String meal1) {
        wait.until(ExpectedConditions.visibilityOf(searchMealField));
        sendKeys(searchMealField, meal1);
        clickOnElement(nameField);
        hoverAndDoubleClick(mealsFoundIcon);
        clickOnElement(okConfirmationOfMealsButton);
        logger.info("<<<<<<<<<< First product is chosen: "+ meal1);}


    public void chooseAnotherProduct(String meal) {
        wait.until(ExpectedConditions.elementToBeClickable(searchMealField));
        clearFieldAndSendKeys(searchMealField, meal);
        clickOnElement(nameField);
        try {
            hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            hoverAndDoubleClick(mealsFoundIcon);
            clickOnElement(okConfirmationOfMealsButton);
            logger.info("<<<<<<<<<< Another product is chosen: "+ meal);
        }
    }

    public ModalPopUpPage fillMealsField(String meal1, String meal2, String meal3) {
        try {
            hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
            chooseFirstProduct(meal1);
            Thread.sleep(1000);
            hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
            chooseAnotherProduct(meal2);
            Thread.sleep(1000);
            hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
            chooseAnotherProduct(meal3);
            logger.info("<<<<<<<<<< Meals field is filled");
            }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }return this;
    }

    public void openBonusMealList() {
        clickOnElement(iconForOpenBonusMealList);
    }

    public void selectBonusMeal() {
        chooseRandomValueFromList(bonusMealList);
    }

    public ModalPopUpPage fillBonusMealField() {
        openBonusMealList();
        selectBonusMeal();
        logger.info("<<<<<<<<<< Bonus Meal field is filled");
        return this;
    }

    public void confirmModalWindow() {
        clickOnElement(confirmModalWindowButton);
        logger.info("<<<<<<<<<< Modal window is confirmed");
    }
}
