package io.github.ungman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    static String URL = "https://www.mvideo.ru/cart";
    WebDriver webDriver;
    @FindBy(name = "/atg/commerce/order/purchase/CartModifierFormHandler.moveToPurchaseInfoByCommerceId")
    private WebElement continueOrderButton;

//    @FindBy(xpath = "//input[@value=''")
    @FindBy(css = "#courier_ci57046207847")
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

    public void clickToContinueOrderButton() {
        new Actions(webDriver)
                .click(continueOrderButton)
                .build()
                .perform();
    }

    public CartPage clickToDelivery(){
        new Actions(webDriver)
                .click(withCurierButton)
                .build()
                .perform();
        return this;
    }

}
