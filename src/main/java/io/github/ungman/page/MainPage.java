package io.github.ungman.page;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends OwnPage {

    static String URL = "https://www.mvideo.ru";
    private final WebDriver webDriver;
    @Getter
    @FindBy(xpath = "//*[@id=\"frm-search-text\"]")
    private WebElement searchProductInput;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public MainPage navigate() {
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
        setDataToFieldWithoutDelete(searchProductInput, "");
        return new SearchResultPage(this.webDriver);
    }

    public CartPage goCartPage() {
        return new CartPage(this.webDriver).navigate();
    }

    public MainPage inputTextToSearchInputField(String text) {
        setDataToField(searchProductInput, text);
        return this;
    }

    public CartPage addUniqueGoodToCart(String product) {
        return this.navigate()
                .maximizeWindow()
                .inputTextToSearchInputField(product)
                .sendInputData()
                .clickOnAddToBasketButton(product);
    }


    public void closeNotification() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.alert = function() {}; window.prompt = function() {return null}; window.confirm = function() {return true}");
        } catch (Exception e) {
            System.out.println("Close popup;");
        }
    }
}
