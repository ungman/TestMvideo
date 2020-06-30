package io.github.ungman.page;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends OwnPage {

    private final WebDriver webDriver;
    private String url;
    @FindBy(css = ".o-pay__content")
    private WebElement blockWithButton;
    @FindBy(xpath = "//input[contains(@class,'add-to-basket-button') and contains(@type,'submit')]")
    private WebElement buttonAddToCart;
//    body > div.c-popup.c-popup_extra-large.c-popup_with-button.c-popup_with-title.c-popup_add-to-basket.c-popup_affix.c-popup_active > div > div.c-popup__content > div > div > a.c-btn.c-btn_text.c-btn_redirect.u-mt-16.u-mt-xs-20.u-ml-12.c-popup-add-to-basket__btn

    //    /html/body/div[6]/div/div[2]/div/div/a[2]
//    @FindBy(css="div.cart-popup-actions.cart-popup-content__bottom-actions > button.button.button_red.button_wide")
    @FindBy(xpath = "//a[@data-sel=\"page_name-a-cart_redirect\"]")
    private WebElement buttonGoToCard;
    //    @FindBy(xpath = "/html/body/div[5]/div/div[2]/div/div/a[1]")
//    @FindBy(css="div.cart-popup-actions.cart-popup-content__bottom-actions > button.button.button_white.button_wide")
    @FindBy(xpath = "//a[@data-sel=\"page_name-a-close_popup\"]")
    private WebElement buttonReturnToShop;

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public ProductPage(WebDriver webDriver, String url) {
        super(webDriver);
        this.webDriver = webDriver;
        this.url = url;
        PageFactory.initElements(webDriver, this);
    }

    public ProductPage navigate() {
        webDriver.navigate().to(url);
        return this;
    }

    @SneakyThrows
    public ProductPage clickToAddCart() {
        click(buttonAddToCart);
        return this;
    }


    public CartPage clickToCartPage() {
        click(buttonGoToCard);
        return new CartPage(webDriver);
    }

    public CartPage clickReturnToShop() {
        click(buttonReturnToShop);
        return new CartPage(webDriver);
    }

}
