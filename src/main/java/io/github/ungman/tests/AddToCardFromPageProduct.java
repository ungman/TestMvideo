package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddToCardFromPageProduct extends WevDriverRunner {


    @Test(dataProvider = "productInfo")
    public void addToCardFromPageProduct(String url, String productInfo) {
        /* way 1 */
        webDriver.get(url);
        getElement(By.xpath("//*[@class='o-pay__content']"))
                .findElement(By.xpath("//*[@type='submit' and  @value='Добавить в корзину']"))
                .click();
    /*
        way 2
        WebElement blockWithButton=getBlockWithToBasketButton();
        WebElement buttonAddToBasket=getButtonAddToBasket(blockWithButton);
        blockWithButton.click();
    */
        webDriver.navigate().to("https://www.mvideo.ru/cart");
        WebElement cardProduct = getCardProductInCartPage(productInfo);
        AssertJUnit.assertNotNull("Product dont have in basket", cardProduct);
    }

    public WebElement getBlockWithToBasketButton() {
        return getElement(By.xpath("//*[@class='o-pay__content']"));
    }

    public WebElement getButtonAddToBasket(WebElement blockWithButton) {
        return blockWithButton.findElement(By.xpath("//*[@type='submit' and  @value='Добавить в корзину']"));
    }

    public WebElement getCardProductInCartPage(String productInfo) {
        for (WebElement element : getElements(By.xpath("//*[@class='c-cart-item__wrapper']"))) {
            System.out.println(element.getText());
            if (element.findElement(By.xpath("//*[contains(text(),'" + productInfo + "')]")) != null)
                return element;
        }
        return null;
    }

    @DataProvider
    public static Object[][] productInfo() {
        return new Object[][]{
                {"https://www.mvideo.ru/products/besprovodnoe-zaryadnoe-ustroistvo-rivacase-rivapower-va4911-fast-charger-50053470", "Беспроводное зарядное устройство RIVACASE RivaPower VA4911 Fast Charger"}
        };
    }

}
