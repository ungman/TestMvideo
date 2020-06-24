package io.github.ungman.page;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {

    private WebDriver webDriver;
    @Getter
    @FindBy(xpath = "//*[@id=\"frm-search-text\"]")
    private WebElement searchProductInput;
    @Getter
    private WebElement blockWithSearchResult;

    static String URL = "https://www.mvideo.ru";

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

    public CartPage goCartPage() {
        this.webDriver.navigate().to(CartPage.URL);
        return new CartPage(this.webDriver);
    }

    public MainPage inputTextToSearchInputField(String text) {
        searchProductInput.sendKeys("");
        searchProductInput.sendKeys(text);
        return this;
    }

    public MainPage inputEnterToSearchInputField() {
        searchProductInput.sendKeys(Keys.ENTER);
        initBlockWithSearchResult();
        return this;
    }

    public WebElement getCardProductInBlockSearchResult(String text) {
        for (WebElement productCard : blockWithSearchResult.findElements(By.xpath("//*[@class='c-product-tile sel-product-tile-main']"))) {
            if (productCard.findElements(By.xpath("//h4[@title ='" + text + "'")) != null) {
                return productCard;
            }
        }
        return null;
    }

    private void initBlockWithSearchResult() {
        closeIFrameWithNotification();
        String xpath = "//*[@id=\"js-product-tile-list\"]/div[@class='product-tiles-list']";
        this.blockWithSearchResult = this.webDriver.findElement(By.xpath(xpath));
    }


    private void closeIFrameWithNotification() {
        WebElement frameNotificationElement = this.webDriver.findElement(By.cssSelector(".flocktory-widget"));
        if (frameNotificationElement != null) {
            try {
                this.webDriver.switchTo()
                        .frame(frameNotificationElement)
                        .findElement(By.xpath("//a[@class='close']"))
                        .click();
            } catch (Exception exception) {
                System.out.println("Cant close windows");
            }

        }
        this.webDriver.switchTo().defaultContent();
    }


//    private boolean isAlertPresent()
//    {
//        try
//        {
//            driver.switchTo().alert();
//            return true;
//        }   // try
//        catch (NoAlertPresentException Ex)
//        {
//            return false;
//        }   // catch
//    }   // isAlertPresent()
}
