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

    private void clickCalendarIcon() {
        clickOnElement(calendarIcon);
    }

    private void chooseRandomDayOnCalendar() {
        chooseRandomValueFromList(daysOfMonth);
    }

    public ModalPopUpPage fillDateField() {
        clickCalendarIcon();
        chooseRandomDayOnCalendar();
        logger.info("<<<<<<<<<< Date field is filled");
        return this;
    }

    private void clickChefFieldOpenButton() {
        clickOnElement(chefFieldOpenButton);
    }

    private void chooseChef() {
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

    private void chooseProduct(String meal) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchMealField));
            clearFieldAndSendKeys(searchMealField, meal);
//        trzeba zmienić fokus, bo nie zawsze wyszukiwanie dziala bez tej zmiany
            clickOnElement(nameField);
            try {
                hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                hoverAndDoubleClick(mealsFoundIcon);
                clickOnElement(okConfirmationOfMealsButton);
                logger.info("<<<<<<<<<< Product is chosen: " + meal);
                Thread.sleep(1000);
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public ModalPopUpPage fillMealsField(String meal1, String meal2, String meal3) {
        hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
        chooseProduct(meal1);
        hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
        chooseProduct(meal2);
        hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
        chooseProduct(meal3);
        logger.info("<<<<<<<<<< Meals field is filled");
        return this;
    }

    private void openBonusMealList() {
        clickOnElement(iconForOpenBonusMealList);
    }

    private void selectBonusMeal() {
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
