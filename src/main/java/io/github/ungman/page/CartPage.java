package io.github.ungman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    static String URL = "https://www.mvideo.ru/cart";
    WebDriver webDriver;
    @FindBy(name = "/atg/commerce/order/purchase/CartModifierFormHandler.moveToPurchaseInfoByCommerceId")
    private WebElement continueOrderButton;
    @FindBy(css = "div.c-cart__delivery")
    private List<WebElement> listDelivery;
    //    @FindBy(xpath = "//input[@value=''")



    @FindBy(css = "#checkout_main > div.o-checkout__step.u-pt-4 > div:nth-child(6) > ul > li:nth-child(2)> input[type='radio']")
    private WebElement withCurierButton;

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriver.navigate().to(URL);
    }

    public WebElement getCardProduct(String productInfo) {
        for (WebElement element : webDriver.findElements(By.className("c-cart-item__wrapper"))) {
            if (element.findElement(By.xpath("//*[contains(text(),'" + productInfo + "')]")) != null)
                return element;
        }
        return null;
    }

    public AuthPageInOrder clickToContinueOrderButton() {
        new Actions(webDriver)
                .click(continueOrderButton)
                .build()
                .perform();
        return  new AuthPageInOrder(webDriver);
    }

    private WebElement withButtonDelivery() {
        List<WebElement> listElement = new ArrayList<>();
        for (WebElement webElement : listDelivery) {
            if (webElement.findElements(By.cssSelector("li.o-checkout-radio__item")).size() == 2) {
                return webElement.findElements(By.cssSelector("input.c-radio-button__input")).get(1);
            }
        }
        return null;
    }

    public CartPage clickToButtonDelivery() {
        WebElement webElement = withButtonDelivery();
        System.out.println(webElement);
        webElement.getAttribute("id");
//        webElement.click();
        webElement=webDriver.findElement(By.xpath("//div[contains(text(),' Оформить доставку')]"));
        new Actions(webDriver)
                .moveToElement(webElement)
                .click()
                .build()
                .perform();
        return this;
    }
}
