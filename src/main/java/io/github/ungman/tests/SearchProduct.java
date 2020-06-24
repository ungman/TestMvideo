package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchProduct extends WevDriverRunner {

    private final String url = "https://www.mvideo.ru";

    @Test(dataProvider = "productInfo")
    public void searchProduct(String textToEnterSearchField, String titleProduct) {
        webDriver.get(url);
        webDriver.manage().window().maximize();
        searchProduct(textToEnterSearchField);
        boolean productInSearchResult = isProductInSearchResult(titleProduct);
        AssertJUnit.assertTrue("Product not found", productInSearchResult);
    }


    @Test(dataProvider = "productInfo")
    public void searchProductWithPageObject(String textToEnterSearchField, String titleProduct) {
        webDriver.get(url);
        MainPage mainPage = new MainPage(webDriver);
        WebElement cardProductInBlockSearchResult = mainPage
                .maximizeWindow()
                .inputTextToSearchInputField(textToEnterSearchField)
                .inputEnterToSearchInputField()
                .getCardProductInBlockSearchResult(titleProduct);
        AssertJUnit.assertNotNull("Product not found",cardProductInBlockSearchResult);
    }


    private boolean isProductInSearchResult(String titleProduct) {
        WebElement blockSearchResult = getBlockSearchResult();
        for (WebElement productCard : blockSearchResult.findElements(By.xpath("//*[@class='c-product-tile sel-product-tile-main']"))) {
            if (productCard.findElements(By.xpath("//h4[@title ='" + titleProduct + "'")) != null) {
                return true;
            }
        }
        return false;
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

    @DataProvider(name = "productInfo")
    public static Object[][] productInfo() {
        return new Object[][]{
                {"Xiaomi mi band 3 black", "Фитнес-трекер Xiaomi Mi Band 3 Black (XMSH05HM)"},
//                {"Xiaomi mi band 3 ", "Фитнес-трекер Xiaomi Mi Band 3 Black (XMSH05HM)"},
//                {"Xiaomi mi band  black", "Фитнес-трекер Xiaomi Mi Band 3 Black (XMSH05HM)"},
//                {"Xiaomi  band  black", "Фитнес-трекер Xiaomi Mi Band 3 Black (XMSH05HM)"},
//                {"Xiaomi mi black", "Фитнес-трекер Xiaomi Mi Band 3 Black (XMSH05HM)"},
//                {"Xiaomi mi", "Фитнес-трекер Xiaomi Mi Band 3 Black (XMSH05HM)"}
        };
    }


}
