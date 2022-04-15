package properties;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.readers.YMLreader;

import java.time.Duration;

public class BrowserEnvironment {
    private static String browserName;
    private static int webElementTimeOut;
    private static int webBrowserImplicitTimeOut;

    private static Logger logger = LoggerFactory.getLogger("properties.BrowserEnvironment.class");
    private WebDriver driver;

    public BrowserEnvironment() {
        setBrowserName();
        this.webElementTimeOut = 40;
        this.webBrowserImplicitTimeOut = 15;
        this.initBrowserSettings();
        logger.info("<<<<<<<<<<<<<<<<<< browserName: " + getBrowserName());
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName() {
        YMLreader ymLreader = new YMLreader();
        if (!ymLreader.getConfiguration().getBrowser().getBrowserName().equals("")) {
            this.browserName = ymLreader.getConfiguration().getBrowser().getBrowserName();
        } else {
            this.browserName = "chrome";
        }
    }

    private void initBrowserSettings() {
        YMLreader ymLreader = new YMLreader();
        this.webElementTimeOut = ymLreader.getConfiguration().getBrowser().getWebElementTimeOut()!=0 ? ymLreader.getConfiguration().getBrowser().getWebElementTimeOut() : this.webElementTimeOut;
        this.webBrowserImplicitTimeOut = ymLreader.getConfiguration().getBrowser().getWebBrowserImplicitTimeOut() !=0 ? ymLreader.getConfiguration().getBrowser().getWebBrowserImplicitTimeOut() : this.webBrowserImplicitTimeOut;
    }

    public WebDriver getDriver() {
        WebDriver driver;
        switch (this.browserName) {
            case "chrome":
                ChromeOptions optionsChrome = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                optionsChrome.addArguments("start-maximized");
                driver = new ChromeDriver(optionsChrome);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(webBrowserImplicitTimeOut));
                driver.get(System.getProperty("appUrl"));
                break;
            case "firefox":
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                optionsFirefox.addArguments("start-maximized");
                driver = new FirefoxDriver(optionsFirefox);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(webBrowserImplicitTimeOut));
                driver.get(System.getProperty("appUrl"));
                break;
            default:
                InternetExplorerOptions optionsdefault = new InternetExplorerOptions();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(optionsdefault);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(webBrowserImplicitTimeOut));
                driver.get(System.getProperty("appUrl"));
        }
        this.driver = driver;
        return this.driver;
    }

    public static int getWebElementTimeOut() {
        return webElementTimeOut;
    }

    public static int getWebBrowserImplicitTimeOut() {
        return webBrowserImplicitTimeOut;
    }

}
