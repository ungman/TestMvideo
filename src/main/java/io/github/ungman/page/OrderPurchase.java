package io.github.ungman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class OrderPurchase {
    private final WebDriver webDriver;
    @FindBy(css = "#delivery-form-content > div > div:nth-child(4) > button")
    private WebElement chooseShop;
    @FindBy(css = "#payment-form > ul > li:nth-child(1)")
    private WebElement cashButtonChoose;
    @FindBy(css = "#payment-form > ul > li:nth-child(2)")
    private WebElement cardButtonChoose;
    @FindBy(css = "#payment-form > ul > li:nth-child(3)")
    private WebElement creditButtonChoose;
    @FindBy(css = "#payment-form > ul > li:nth-child(4)")
    private WebElement credit0ButtonChoose;

    @FindBy(css = "#promo-checkbox-15930736381641")
    private WebElement promo;

    @FindBy(id = "field_8")
    private WebElement fieldEmail;
    @FindBy(id = "myPhone")
    private WebElement fieldPhone;
    @FindBy(id = "field_6")
    private WebElement fieldName;

    private WebElement fieldAnotherRecipient;
    @FindBy(className = "c-cost-line__text")
    private WebElement costGoods;

    private WebElement changeToShopListButton;
    private List<WebElement> shopList;

    public OrderPurchase(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public OrderPurchase clickToChooseShop() {
        System.out.println("not worked");
//
//        new Actions(webDriver)
//                .click(chooseShop)
//                .build()
//                .perform();
        return this;
    }

    private OrderPurchase initChangeToShopListButton() {
        System.out.println("not worked");

//        WebElement changeToShopList = new WebDriverWait(webDriver, Duration.ofSeconds(5), Duration.ofMillis(250)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#store-locator-list-input")));
//        if (changeToShopList == null) {
//            List<WebElement> iframes = webDriver.findElements(By.tagName("iframe"));
//            for (WebElement iframe : iframes) {
//                webDriver.switchTo().frame(iframe);
//                changeToShopList = new WebDriverWait(webDriver, Duration.ofSeconds(5), Duration.ofMillis(250)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#store-locator-list-input")));
//                if (changeToShopList != null) {
//                    new Actions(webDriver)
//                            .moveToElement(changeToShopList)
//                            .click()
//                            .build()
//                            .perform();
//                    changeToShopListButton = changeToShopList;
//                    initListShop();
//                    break;
//                }
//                webDriver.switchTo().defaultContent();
//            }
//        }else {
//            new Actions(webDriver)
//                    .moveToElement(changeToShopList)
//                    .click()
//                    .build()
//                    .perform();
//            changeToShopListButton = changeToShopList;
//            initListShop();
//        }
//
//        webDriver.switchTo().defaultContent();
//        this.changeToShopListButton = webDriver.findElement(By.id("store-locator-list-input"));
        return this;
    }

    public OrderPurchase clickShopList() {
        System.out.println("not worked");
//        initChangeToShopListButton();
//        initListShop();
        return this;
    }

    private void initListShop() {
        this.shopList = webDriver.findElements(By.cssSelector("#store-locator-list > ul > li.c-store-list__item clearfix"));
    }

    public OrderPurchase setShop(int index) {
        System.out.println("not worked");
//
//        if (index < shopList.size()) {
//            new Actions(webDriver)
//                    .click(shopList.get(index))
//                    .build()
//                    .perform();
//        }
        return this;
    }

    public OrderPurchase setDataToFieldEmail(String text) {
        new Actions(webDriver)
                .sendKeys(fieldEmail, text)
                .build()
                .perform();
        return this;
    }

    public OrderPurchase setDataToFieldPhone(String text) {
        new Actions(webDriver)
                .sendKeys(fieldPhone, text)
                .build()
                .perform();
        return this;
    }

    public OrderPurchase setDataToFieldName(String text) {
        new Actions(webDriver)
                .sendKeys(fieldName, text)
                .build()
                .perform();
        return this;
    }

    public OrderPurchase setDataToFieldAnotherRecipient(String text) {
        if (fieldAnotherRecipient == null)
            fieldAnotherRecipient = webDriver.findElement(By.cssSelector("#anotherRecipient"));
        new Actions(webDriver)
                .sendKeys(fieldAnotherRecipient, text)
                .build()
                .perform();
        return this;
    }

    public OrderPurchase payCash() {
        clickWithActions(cashButtonChoose);
        return this;
    }

    public OrderPurchase payCard() {
        clickWithActions(cardButtonChoose);
        return this;
    }

    public OrderPurchase payCredit() {
        clickWithActions(creditButtonChoose);
        return this;
    }

    public OrderPurchase payCredit0() {
        clickWithActions(credit0ButtonChoose);
        return this;
    }

    private void clickWithActions(WebElement webElement) {
        new Actions(webDriver)
                .click(webElement)
                .build()
                .perform();
    }
}
