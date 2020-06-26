package io.github.ungman.page;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    private final WebDriver webDriver;
    private String url;

    @FindBy(css = ".o-pay__content")
    private WebElement blockWithButton;
    @FindBy(xpath = "//input[contains(@class,'add-to-basket-button') and contains(@type,'submit')]")
    private WebElement buttonAddToCart;


    @FindBy(xpath = "/html/body/div[13]/div/div[2]/div/div/a[2]")
    private WebElement buttonGoToCard;
//    @FindBy(xpath = "/html/body/div[5]/div/div[2]/div/div/a[1]")
    private WebElement buttonReturnToShop;

    public ProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public ProductPage(WebDriver webDriver, String url) {
        this.webDriver = webDriver;
        this.url = url;
        webDriver.navigate().to(url);
        PageFactory.initElements(webDriver, this);
    }

    @SneakyThrows
    public ProductPage clickToAddCart() {
//        new Actions(webDriver)
//                .click(buttonAddToCart)
//                .build()
//                .perform();
        blockWithButton.click();
        return this;
    }
    private CartPage goToCartPage() {
        return new CartPage(webDriver);
    }

    public CartPage clickToCartPage() {
        new Actions(webDriver)
                .click(buttonAddToCart)
                .build()
                .perform();
        return goToCartPage();
    }

    public CartPage clickReturnToShop() {
        new Actions(webDriver)
                .click(buttonReturnToShop)
                .build()
                .perform();
        return goToCartPage();
    }

}
