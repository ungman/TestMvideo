package io.github.ungman.page;

import lombok.SneakyThrows;
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

    @FindBy(xpath = "/html/body/div[13]/div/div[2]/div/div/a[2]")
    private WebElement buttonGoToCard;
    @FindBy(xpath = "/html/body/div[5]/div/div[2]/div/div/a[1]")
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

    public ProductPage navigate(){
        webDriver.navigate().to(url);
        return this;
    }
    @SneakyThrows
    public ProductPage clickToAddCart() {
        click(buttonAddToCart);
        return this;
    }

    private CartPage goToCartPage() {
        return new CartPage(webDriver);
    }

    public CartPage clickToCartPage() {
        click(buttonAddToCart);
        return goToCartPage();
    }

//    public CartPage clickReturnToShop() {
//        click(buttonReturnToShop);
//        return goToCartPage();
//    }

}
