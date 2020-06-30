package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.SearchResultPage;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddProductToCardFromSearchPage extends WevDriverRunner {

    @DataProvider(name = "productInfo")
    public static Object[][] productInfo() {
        return new Object[][]{
                {"https://www.mvideo.ru/product-list-page-cls?q=Xiaomi+mi+band+3+black&region_id=1&limit=12", "Фитнес-трекер Xiaomi Mi Band 3 Black (XMSH05HM)"}
        };
    }

    @Test(dataProvider = "productInfo")
    public void addProductToCardWithPageObject(String url, String productName) {
        WebElement cardProduct = new SearchResultPage(webDriver, url)
                .navigate()
                .clickOnAddToBasketButton(productName)
                .getCardProduct(productName);
        AssertJUnit.assertNotNull("Product not found in cart page", cardProduct);
    }


}
