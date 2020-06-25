package io.github.ungman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage {

    private final WebDriver webDriver;
    private WebElement blockWithSearchResult;
    private WebElement addToBasketButton;

    public SearchResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

    }

    public SearchResultPage(WebDriver webDriver, String url) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriver.navigate().to(url);
    }

    private void initBlockWithSearchResult() {
        closeNotification();
        String xpath = "//*[@id=\"js-product-tile-list\"]";
        this.blockWithSearchResult = this.webDriver.findElement(By.xpath(xpath));
    }

    public WebElement getCardProductInBlockSearchResult(String text) {
        initBlockWithSearchResult();
        for (WebElement productCard : blockWithSearchResult.findElements(By.cssSelector(".c-product-tile.sel-product-tile-main"))) {
            if (productCard.findElements(By.xpath("//h4[@title ='" + text + "']")) != null) {
                return productCard;
            }
        }
        return null;
    }

    public CartPage clickOnAddToBasketButton(String nameProduct) {
        initBlockWithSearchResult();
        initButtonAddToBasket(nameProduct);
        new Actions(webDriver)
                .click(addToBasketButton)
                .build()
                .perform();
        return new CartPage(webDriver);
    }

    private void initButtonAddToBasket(String text) {
        addToBasketButton = getCardProductInBlockSearchResult(text)
                .findElement(By.xpath("//button[@name='/atg/commerce/order/purchase/CartModifierFormHandler.addItemToOrder']"));
    }

    public void closeNotification() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.alert = function() {}; window.prompt = function() {return null}; window.confirm = function() {return true}");
        } catch (Exception e) {
            System.out.println("Close popup;");
        }
    }

    private void closeIFrameWithNotification() {
        WebElement frameNotificationElement = this.webDriver.findElement(By.cssSelector(".flocktory-widget"));
        if (frameNotificationElement != null) {
            try {
                new WebDriverWait(webDriver, Duration.ofSeconds(3), Duration.ofMillis(250))
                        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNotificationElement));
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


//    public SearchResultPage getCardProductInBlockSearchResult(){}

}
