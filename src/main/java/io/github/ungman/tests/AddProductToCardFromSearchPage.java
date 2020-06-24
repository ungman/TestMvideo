package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddProductToCardFromSearchPage extends WevDriverRunner {

    private final String url = "https://www.mvideo.ru";

    @Test(dataProvider = "productInfo")
    public void addProductToCard(String textSearch, String productName) {
        String urlCart = "https://www.mvideo.ru";
        this.webDriver.get(url);
        preCondition(textSearch, productName);

        webDriver.navigate().to(urlCart);
        AssertJUnit.assertNotNull("Product not found in cart page", getCardProductInCartPage(productName));
    }

    private void preCondition(String product, String productName) {
        searchProduct(product);
        clickToButtonAddToBasket(getProductCard(productName));
    }

    private WebElement getProductCard(String titleProduct) {
        WebElement blockSearchResult = getBlockSearchResult();
        for (WebElement productCard : blockSearchResult.findElements(By.xpath("//*[@class='c-product-tile sel-product-tile-main']"))) {
            if (productCard.findElements(By.xpath("//h4[@title ='" + titleProduct + "'")) != null) {
                return productCard;
            }
        }
        return null;
    }

    private void clickToButtonAddToBasket(WebElement productCard) {
        productCard
                .findElement(By.xpath("//*[contains(@class,'btn add-to-basket-button i-icon-buy submit-basket')]"))
                .click();
    }

    private void searchProduct(String textToEnterSearchField) {
        WebElement inputFormSearch = getInputSearchField();
        inputFormSearch.sendKeys("");
        inputFormSearch.sendKeys(textToEnterSearchField);
        inputFormSearch.sendKeys(Keys.ENTER);
    }

    private WebElement getInputSearchField() {
        return getElement(By.xpath("//*[@id=\"frm-search-text\"]"));
    }

    private WebElement getBlockSearchResult() {
        return getElement(By.xpath("//*[@id=\"js-product-tile-list\"]/div[@class='product-tiles-list']"));
    }

    public WebElement getCardProductInCartPage(String productInfo) {
        for (WebElement element : getElements(By.xpath("//*[@class='c-cart-item__wrapper']"))) {
            if (element.findElement(By.xpath("//*[contains(text(),'" + productInfo + "')]")) != null)
                return element;
        }
        return null;
    }

    @DataProvider(name = "productInfo")
    public static Object[][] productInfo() {
        return new Object[][]{
                {"Xiaomi mi band 3 black", "Фитнес-трекер Xiaomi Mi Band 3 Black (XMSH05HM)"}
        };
    }
}
