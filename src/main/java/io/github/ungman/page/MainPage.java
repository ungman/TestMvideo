package io.github.ungman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class MainPage extends OwnPage {

    private static final String URL = "https://www.mvideo.ru/";
    private final WebDriver webDriver;
    @FindBy(xpath = "//*[@id=\"frm-search-text\"]")
    private WebElement searchProductInput;
    @FindBy(css = "#js-mini-basket")
    private WebElement basketButton;
    @FindBy(css = "#js-mini-basket > a > i > span")
    private WebElement spanWithCountGoodsInCart;
    @FindBy(css = "div.tooltipster-box > span")
    private WebElement popupCityClose;
    private List<WebElement> itemsOnCart;
    private boolean isClosedPopup = false;
    private boolean firstLaunch = true;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public static String getUrl() {
        return URL;
    }

    public MainPage navigate() {
//        System.out.printf("Current url: %s %s  \n", webDriver.getCurrentUrl(), URL);

        if (!webDriver.getCurrentUrl().equalsIgnoreCase(URL)) {
            webDriver.navigate().to(URL);
        }
        return this;
    }

    private void closePopupCityChoose() {
        if (popupCityClose != null && !isClosedPopup && firstLaunch) {
            try {
                click(popupCityClose);
                isClosedPopup = true;
            } catch (Exception e) {
                System.out.println("Popup not found");
            }
        }
    }

    public MainPage isFirstLaunch(boolean status) {
        this.firstLaunch = status;
        return this;
    }

    public MainPage navigateReload() {
        System.out.println(URL);
        webDriver.navigate().to(URL);
        return this;
    }

    public String getTitle() {
        return this.webDriver.getTitle();
    }

    public MainPage maximizeWindow() {
        this.webDriver.manage().window().maximize();
        return this;
    }

    public SearchResultPage sendInputData() {
//        setDataToField(searchProductInput, Keys.ENTER);
        setDataToField(searchProductInput,"");
        return new SearchResultPage(this.webDriver);
    }

    public CartPage goCartPage() {
        return new CartPage(this.webDriver).navigate();
    }

    public SearchResultPage inputTextToSearchInputField(String text) {
        setDataToField(searchProductInput, text);
        return new SearchResultPage(this.webDriver);
    }

    public CartPage addUniqueGoodToCart(String product) {
        return this.navigate()
                .maximizeWindow()
                .inputTextToSearchInputField(product)
                .clickOnAddToBasketButton(product);
//                .navigate();
    }

    public CartPage addUniqueGoodToCartWithoutLoadPage(String product) {
        return this.maximizeWindow()
                .inputTextToSearchInputField(product)
                .clickOnAddToBasketButton(product);
    }

    public String getCountItemInCart() {
        navigate();
        try {
            initListItemsOnBasket();
            if (basketButton != null && itemsOnCart != null) {
                this.moveToElement(basketButton);
                return getText(spanWithCountGoodsInCart);
            }
        } catch (Exception e) {
            System.out.println("dont found elem");
        }
        return null;
    }

    public boolean itemInCart(String product) {
        navigate();
        initListItemsOnBasket();
        if (basketButton != null && itemsOnCart != null) {
            return itemsOnCart.stream()
                    .anyMatch(el -> {
                        int maxLenTitleInCard = 35;
                        String product1 = (product.length() > maxLenTitleInCard) ? product.substring(0, maxLenTitleInCard) : product;
                        System.out.println(product1);
                        return getTextWithoutWait(el).toLowerCase().contains(product1.toLowerCase());
                    });
        }
        return false;
    }

    public CartPage addMultiItem(String product) {
        if (!itemInCart(product)) {
            return new MainPage(webDriver).addUniqueGoodToCart(product);
        } else {
            return new CartPage(webDriver).navigate().addToGoods(product);
        }
    }

    private void initListItemsOnBasket() {
//        if(webDriver.getCurrentUrl().equalsIgnoreCase(URL))
//            navigateReload();
        moveToElement(basketButton);
        itemsOnCart = webDriver.findElements(By.cssSelector("h4.mini-basket-product-name > div.sel-mini-cart-prod-title"));
    }
}
