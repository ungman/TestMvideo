package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.MainPage;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchProduct extends WevDriverRunner {

    private final String url = "https://www.mvideo.ru";

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

    @Test(dataProvider = "productInfo")
    public void searchProductWithPageObject(String textToEnterSearchField, String titleProduct) {
        webDriver.get(url);
        WebElement cardProduct = new MainPage(webDriver)
                .maximizeWindow()
                .inputTextToSearchInputField(textToEnterSearchField)
                .sendInputData()
                .getCardProductInBlockSearchResult(titleProduct);
        AssertJUnit.assertNotNull("Product not found", cardProduct);
    }


}
