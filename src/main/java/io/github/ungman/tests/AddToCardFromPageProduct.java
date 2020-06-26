package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.ProductPage;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddToCardFromPageProduct extends WevDriverRunner {

    @DataProvider
    public static Object[][] productInfo() {
        return new Object[][]{
                {"https://www.mvideo.ru/products/besprovodnoe-zaryadnoe-ustroistvo-rivacase-rivapower-va4911-fast-charger-50053470", "Беспроводное зарядное устройство RIVACASE RivaPower VA4911 Fast Charger"}
        };
    }

    @Test(dataProvider = "productInfo")
    public void addToCardWithPageObject(String url, String productInfo) {
        WebElement cardProduct = new ProductPage(webDriver, url)
                .clickToAddCart()
                .clickToCartPage()
                .getCardProduct(productInfo);
        AssertJUnit.assertNotNull("Product dont have in basket", cardProduct);
    }

}
