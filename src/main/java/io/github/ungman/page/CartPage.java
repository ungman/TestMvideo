package io.github.ungman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends OwnPage {

    String url = "https://www.mvideo.ru/cart";
    WebDriver webDriver;
    @FindBy(name = "/atg/commerce/order/purchase/CartModifierFormHandler.moveToPurchaseInfoByCommerceId")
    private WebElement continueOrderButton;
    @FindBy(css = "div.c-cart__delivery")
    private List<WebElement> listDelivery;
    @FindBy(css = "#ui-id-1 > div.c-orders-list.c-orders-list_mobile-sheet.c-cart__order_block-2 > div > div > div > div > div.c-cost-line__text-wrap > span")
    private WebElement orderPrice;
    //    @FindBy(xpath = "//div[contains(@class,'o-checkout__back-shopping-top')]/a")
//    @FindBy(xpath = "/html/body/div[4]/div[1]/div[2]/div[1]/a")
//    @FindBy(xpath = "//a[contains(@data-sel='new_cart-link-back_to_shopping']")
    private WebElement returnToShopButton;
    @FindBy(xpath = "//*[@id=\"ui-id-1\"]/div[3]/div/div/div/div/div[2]/span")
    private WebElement spanWithOrderPrice;
    @FindBy(css = "#checkout_main > div.o-checkout__step.u-pt-4 > div:nth-child(6) > ul > li:nth-child(2)> input[type='radio']")
    private WebElement withCurierButton;
    @FindBy(css = ".c-link.c-cart-item__title")
    private List<WebElement> cardGoodsText;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
//        navigate();
//        System.out.println("url cart page: " + webDriver.getCurrentUrl());
//        clickToReturnShop();
    }

    public CartPage navigate() {
        if (!webDriver.getCurrentUrl().startsWith(url))
            webDriver.navigate().to(url);
        return this;
    }

    public WebElement getCardProduct(String productInfo) {
        for (WebElement element : webDriver.findElements(By.className("c-cart-item__wrapper"))) {
            if (element.findElement(By.xpath("//*[contains(text(),'" + productInfo + "')]")) != null)
                return element;
        }
        return null;
    }

    public AuthPageInOrder clickToContinueOrderButton() {
        click(continueOrderButton);
        return new AuthPageInOrder(webDriver);
    }

    private WebElement elementWithButtonDelivery() {
        List<WebElement> listElement = new ArrayList<>();
        for (WebElement webElement : listDelivery) {
            if (webElement.findElements(By.cssSelector("li.o-checkout-radio__item")).size() == 2) {
                return webElement.findElements(By.cssSelector("input.c-radio-button__input")).get(1);
            }
        }
        return null;
    }

    public CartPage clickToButtonDelivery() {
        WebElement elementWithButtonDelivery = webDriver.findElement(By.xpath("//div[contains(text(),'Оформить доставку')]"));
        click(elementWithButtonDelivery);
        return this;
    }

    public String geOrderCost() {
        return orderPrice.getText();
    }

    public MainPage clickToReturnShop() {
//        returnToShopButton = webDriver.findElement(By.partialLinkText("Вернуться к покупкам"));
//        returnToShopButton = webDriver.findElement(By.partialLinkText("Вернуться к покупкам"));
        returnToShopButton = initWithVisible(By.cssSelector("#ui-id-1 > div.o-checkout__back-shopping.hidden-tablet.u-mb-xs-4 > a"));
//        new Actions(webDriver).
//        click(returnToShopButton);
        return new MainPage(webDriver);
    }

    public int getOrderPrice() {
        String orderPrice = spanWithOrderPrice.getText();
        orderPrice = orderPrice.replaceAll("\\D+", "");
        return Integer.parseInt(orderPrice);
    }

    public CartPage addToGoods(String text) {
//        navigate();
        try {
            WebElement item = webDriver.findElement(By.xpath("//a[contains(@class,'c-link c-cart-item__title') and contains(text(),'" + text + "')]"));
//            WebElement item = webDriver.findElement(By.xpath("//a[contains(@class,'c-link c-cart-item__title') and normalize-space(.,'" + text + "')]"));
            String idGoods = item.getAttribute("data-holder").replaceAll("[^0-9.]", "");
            WebElement incrButton = webDriver.findElement(By.xpath("//button[@data-holder='#counterInputChange" + idGoods + "' and @data-ga-content='plus']"));
            if (incrButton != null) {
                click(incrButton);
                System.out.println("clicked on increment button");
            } else {
                System.out.println("not found increment button");
            }
        } catch (Exception e) {
            System.out.println("Error on click  or found incrButton");
            e.printStackTrace();
        }
        return this;
    }
//    public CartPage addToGoods(String text) {
//        List<WebElement> itemWrapper = webDriver.findElements(By.className("c-cart-item__wrapper"));
//        boolean found = false;
//        for (WebElement item : itemWrapper) {
//            if (item.findElement(By.xpath("//a[contains(text(),'" + text + "')]")) != null) {
//                WebElement buttonCounterItemIncrementer = item.findElement(By.cssSelector(".c-btn.c-btn_white.c-counter-input__plus-btn.c-counter-input__btn"));
//                found = true;
//                if (buttonCounterItemIncrementer != null) {
//                    click(buttonCounterItemIncrementer);
//                    System.out.println("clicked To Increment");
//                } else {
//                    System.out.println("not found button increment");
//                }
//            }
//        }
//        System.out.println("Founded product in cart page: " + found);
//        return this;
//    }

    public CartPage addToGoods(String text, Integer number) {
        List<WebElement> itemWrapper = webDriver.findElements(By.className("c-cart-item__wrapper "));
        boolean found = false;
        for (WebElement item : itemWrapper) {
            if (item.findElement(By.xpath("//a[contains(text(),'" + text + "')]")) != null) {
                WebElement fieldInputCountGoods = item.findElement(By.cssSelector(".c-counter-input__input valid"));
                found = true;
                if (fieldInputCountGoods != null) {
                    setDataToField(fieldInputCountGoods, String.valueOf(number));
                } else {
                    System.out.println("not inc");
                }
            } else {
                System.out.println("not add");
            }
        }
        System.out.println("Founded: " + found);
        return this;
    }

//    private boolean getInCart(String product) {
//        return cardGoodsText.contains(product);
//    }
}
