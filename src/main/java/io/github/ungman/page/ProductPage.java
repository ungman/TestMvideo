package io.github.ungman.page;

import org.openqa.selenium.By;
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

    private WebElement buttonAddToCart;

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

    public ProductPage clickToAddCart() {
        initButtonAddToCart();
//        buttonAddToCart.click();
        new Actions(webDriver)
                .click(buttonAddToCart)
                .build()
                .perform();
        return this;
    }

    public CartPage goToCartPage() {
        return new CartPage(webDriver);
    }

    private void initButtonAddToCart() {
//        this.buttonAddToCart = blockWithButton.findElement(By.xpath("//*[contains(@type,'submit') and  contains(@value,'Добавить в корзину')]"));
        this.buttonAddToCart = blockWithButton.findElement(By.xpath("//input[contains(@class,'add-to-basket-button') and contains(@type,'submit')]"));
    }

}
