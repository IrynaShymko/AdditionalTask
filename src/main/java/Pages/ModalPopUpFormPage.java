package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ModalPopUpFormPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private static Logger logger = LoggerFactory.getLogger("ModalPopUpFormPage.class");
    private By mealsFoodIcon2XPath = By.xpath("//div[@id='createDinnerMeals-awepw']//li[@class='awe-li']//i");

    public ModalPopUpFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);
    }

    public void clickCalendarIcon() {
        calendarIcon.click();
    }

    public void chooseDayOnCalendar() {
        int index = new Random().nextInt(daysOfMonth.size());
        daysOfMonth.get(index).click();
    }

    public void fillDateField() {
        clickCalendarIcon();
        chooseDayOnCalendar();
    }

    public void clickChefFieldOpenButton() {
        chefFieldOpenButton.click();
    }

    public void chooseChef() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        wait.until(ExpectedConditions.visibilityOf(allShefsElement));
        int index = new Random().nextInt(listOfShefs.size());
        Actions actions = new Actions(driver);
        actions.moveToElement(listOfShefs.get(index)).perform();
        actions.moveToElement(listOfShefs.get(index)).doubleClick().perform();
    }

    public void fillChefField() {
        clickChefFieldOpenButton();
        chooseChef();
    }

    public void chooseFirstProduct(String meal1) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(searchMealField));
        searchMealField.sendKeys(meal1);
        actions = new Actions(driver);
        nameField.click();
        Thread.sleep(2000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(mealsFoundIcon));
        actions.moveToElement(mealsFoundIcon).perform();
        actions.moveToElement(mealsFoundIcon).click().perform();
        okConfirmationOfMealsButton.click();
    }

    public void chooseAnotherProduct(String meal) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(mealsFieldOpenButtonForAddAnotherProduct));
        actions.moveToElement(mealsFieldOpenButtonForAddAnotherProduct).perform();
        actions.moveToElement(mealsFieldOpenButtonForAddAnotherProduct).doubleClick().perform();
        wait.until(ExpectedConditions.visibilityOf(searchMealField));
        searchMealField.clear();
        searchMealField.sendKeys(meal);
        nameField.click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='createDinnerMeals-awepw']//li[@class='awe-li']//i")));
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(mealsFoundIcon));
        actions.moveToElement(mealsFoundIcon).perform();
        actions.moveToElement(mealsFoundIcon).click().perform();
        okConfirmationOfMealsButton.click();
    }

    public void fillMealsField(String meal1, String meal2, String meal3) throws InterruptedException {
        mealsFieldOpenButtonForAddFirstProduct.click();
        chooseFirstProduct(meal1);
        Thread.sleep(2000);

        chooseAnotherProduct(meal2);
        Thread.sleep(2000);

        chooseAnotherProduct(meal3);
    }

    public void openBonusMealList() {
        iconForOpenBonusMealList.click();
    }

    public void selectBonusMeal() {
        int index = new Random().nextInt(bonusMealList.size());
        bonusMealList.get(index).click();
    }

    public void fillBonusMealField() {
        openBonusMealList();
        selectBonusMeal();
    }

    public void confirmModalWindow(){
        confirmModalWindowButton.click();
    }

    public String getTextFromAlert(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String actualAlertMessage=alert.getText();
        return actualAlertMessage;
    }

    public void acceptAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
