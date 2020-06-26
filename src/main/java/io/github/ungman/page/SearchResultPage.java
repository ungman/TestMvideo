package io.github.ungman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends OwnPage {

    private final WebDriver webDriver;

    private WebElement addToBasketButton;

    @FindBy(xpath = "//*[@id=\"js-mini-basket\"]")
    private WebElement buttonGoToBasket;
    @FindBy(className = "mini-basket-total-price__actual")
    private WebElement spanWithOrderPrice;
    @FindBy(xpath = "//*[@id=\"js-product-tile-list\"]")
    private WebElement blockWithSearchResult;

    private String url;

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

    }

    public SearchResultPage(WebDriver webDriver, String url) {
        super(webDriver);
        this.webDriver = webDriver;
        this.url=url;
        PageFactory.initElements(webDriver, this);
    }

    public SearchResultPage navigate() {
        webDriver.navigate().to(url);
        return this;
    }

    private void initBlockWithSearchResult() {

        initWithVisible(blockWithSearchResult);
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
        click(addToBasketButton);
        return new CartPage(webDriver);
    }

    private void initButtonAddToBasket(String text) {
        addToBasketButton = getCardProductInBlockSearchResult(text)
                .findElement(By.xpath("//button[@name='/atg/commerce/order/purchase/CartModifierFormHandler.addItemToOrder']"));
        initWithVisibleAndClickable(addToBasketButton);
    }


    public Double getPrice() {
        moveToElement(buttonGoToBasket);
        String price = "-1";
        try {
            WebElement actualPrice = webDriver.findElement(By.cssSelector(".mini-basket-total-price__actual"));
            initWithVisible(actualPrice);
            if (actualPrice != null) {
                price = actualPrice.getText();
            } else {
                actualPrice = webDriver.findElement(By.cssSelector("mini-basket-total-price__actual"));
                initWithVisible(actualPrice);
                if (actualPrice != null) {
                    price = actualPrice.getText();
                }
            }

        } catch (Exception e) {
            price = "0";
        }
        price = price.replaceAll("\\D+", "");
        return Double.parseDouble(price);
    }


//    public SearchResultPage getCardProductInBlockSearchResult(){}

}
