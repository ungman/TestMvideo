package io.github.ungman.helper;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WevDriverRunner {
    private final String pathToResourcesFolder = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    protected WebDriver webDriver;
    private String webDriverName;
    private Properties properties;

    @BeforeSuite
    public void setUp() {
        try {
            properties = loadPropertyFile();
            setSystemProperties(properties);
            loadPropertyFile();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void setSystemProperties(Properties properties) throws Exception {
        String pathToWebDriver = properties.getProperty("webdriver.path");
        this.webDriverName = properties.getProperty("webdriver.name");
        System.out.println(webDriverName);
        if (this.webDriverName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", pathToWebDriver + properties.getProperty("webriver.exec.chrome"));
        } else if (this.webDriverName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", pathToWebDriver + properties.getProperty("webriver.exec.firefox"));
        } else if (this.webDriverName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", pathToWebDriver + properties.getProperty("webriver.exec.edge"));
        } else {
            throw new Exception("Driver not found.Added custom (coming soon)");
        }

    }

    @NotNull
    private Properties loadPropertyFile() {
        final Properties properties = new Properties();
        String pathFile = this.pathToResourcesFolder + "application.properties";
        try {
            properties.load(new FileInputStream(pathFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @BeforeMethod
    public void initMethod() throws Exception {
        if (this.webDriverName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");
            this.webDriver = new ChromeDriver(chromeOptions);
        } else if (this.webDriverName.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("dom.webnotifications.enable", false);
            this.webDriver = new FirefoxDriver(firefoxOptions);
        } else if (this.webDriverName.equalsIgnoreCase("edge")) {
            this.webDriver = new EdgeDriver();
        } else {
            throw new Exception("Drive not init");
        }
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().deleteAllCookies();
    }

    protected void closeBrowse(WebDriver webDriver) {
        webDriver.close();
    }

    protected WebElement getElement(By by) {
        return this.webDriver.findElement(by);
    }

    public void closeNotification() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.alert = function() {}; window.prompt = function() {return null}; window.confirm = function() {return true}");
        } catch (Exception e) {
            System.out.println("Close popup;");
        }
    }

    protected List<WebElement> getElements(By by) {
        return this.webDriver.findElements(by);
    }

    @AfterMethod
    public void close() {
        this.webDriver.quit();
    }

    @AfterTest
    public void quit() {
        this.webDriver.quit();
    }
}
