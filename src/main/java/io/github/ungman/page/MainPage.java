package io.github.ungman.page;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {

    static String URL = "https://www.mvideo.ru";
    private final WebDriver webDriver;
    @Getter
    @FindBy(xpath = "//*[@id=\"frm-search-text\"]")
    private WebElement searchProductInput;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String getTitle() {
        return this.webDriver.getTitle();
    }

    public MainPage maximizeWindow() {
        this.webDriver.manage().window().maximize();
        return this;
    }

    public SearchResultPage sendInputData() {
        searchProductInput.sendKeys(Keys.ENTER);
        return new SearchResultPage(this.webDriver);
    }

    public CartPage goCartPage() {
        this.webDriver.navigate().to(CartPage.URL);
        return new CartPage(this.webDriver);
    }

    public MainPage inputTextToSearchInputField(String text) {
        searchProductInput.sendKeys("");
        searchProductInput.sendKeys(text);
        return this;
    }


//    private void closeIFrameWithNotification() {
//        WebElement frameNotificationElement = this.webDriver.findElement(By.cssSelector(".flocktory-widget"));
//        if (frameNotificationElement != null) {
//            try {
//                new WebDriverWait(webDriver, Duration.ofSeconds(3), Duration.ofMillis(250))
//                        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNotificationElement));
//                this.webDriver.switchTo()
//                        .frame(frameNotificationElement)
//                        .findElement(By.xpath("//a[@class='close']"))
//                        .click();
//            } catch (Exception exception) {
//                System.out.println("Cant close windows");
//            }
//        }
//        this.webDriver.switchTo().defaultContent();
//    }

    public void closeNotification() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.alert = function() {}; window.prompt = function() {return null}; window.confirm = function() {return true}");
        } catch (Exception e) {
            System.out.println("Close popup;");
        }
    }
}
