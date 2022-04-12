package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
        return this;
    }

    public void clickChefFieldOpenButton() {
        clickOnElement(chefFieldOpenButton);
    }

    public void chooseChef() {
        wait.until(ExpectedConditions.visibilityOf(allShefsElement));
        int index = new Random().nextInt(listOfShefs.size());
        hoverAndDoubleClick(listOfShefs.get(index));
    }

    public ModalPopUpPage fillChefField() {
        clickChefFieldOpenButton();
        chooseChef();
        return this;
    }

    public void chooseFirstProduct(String meal1) {
            wait.until(ExpectedConditions.visibilityOf(searchMealField));
            sendKeys(searchMealField, meal1);
            clickOnElement(nameField);
            hoverAndDoubleClick(mealsFoundIcon);
            clickOnElement(okConfirmationOfMealsButton);
    }

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
        }
    }

    public ModalPopUpPage fillMealsField(String meal1, String meal2, String meal3) {
            hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
            chooseFirstProduct(meal1);

            hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
            chooseAnotherProduct(meal2);

            hoverAndDoubleClick(mealsFieldOpenButtonForAddProduct);
            chooseAnotherProduct(meal3);
        return this;
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
        return this;
    }

    public void confirmModalWindow() {
        clickOnElement(confirmModalWindowButton);
    }
}
