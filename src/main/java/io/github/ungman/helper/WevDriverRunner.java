package io.github.ungman.helper;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WevDriverRunner {
    private String webDriverName;
    private final String pathToResourcesFolder = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    protected WebDriver webDriver;


    @BeforeSuite
    public void setUp() {
        try {
            final Properties properties = loadPropertyFile();
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
            this.webDriver = new ChromeDriver();
        } else if (this.webDriverName.equalsIgnoreCase("firefox")) {
            this.webDriver = new FirefoxDriver();
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
    protected WebElement getElement(By by){
        return  this.webDriver.findElement(by);
    }

    protected List<WebElement> getElements(By by){
        return  this.webDriver.findElements(by);
    }
    @AfterMethod
    public void close() {
        if (webDriver != null)
            this.webDriver.close();
    }
}
