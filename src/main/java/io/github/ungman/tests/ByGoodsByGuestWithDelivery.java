package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.AuthPageInOrder;
import io.github.ungman.page.CartPage;
import io.github.ungman.page.OrderPurchase;
import io.github.ungman.page.ProductPage;
import lombok.SneakyThrows;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ByGoodsByGuestWithDelivery extends WevDriverRunner {

    @DataProvider
    public static Object[][] dataMultiGoods() {
        return new Object[][]{
                {"https://www.mvideo.ru/products/fitnes-treker-xiaomi-mi-band-3-black-xmsh05hm-30040243",
                        "https://www.mvideo.ru/products/holodilnik-indesit-itf-018-w-20051784",
                        "Мы не знаем такой город. Проверьте его написание или укажите другой."
                }
        };
    }

    @DataProvider
    public static Object[][] dataOneGood() {
        return new Object[][]{
                {
                        "https://www.mvideo.ru/products/holodilnik-indesit-itf-018-w-20051784",
                        "Мы не знаем такой город. Проверьте его написание или укажите другой."
                }
        };
    }

    @SneakyThrows
    public void withOneGoods(String url) {
        new ProductPage(webDriver, url)
                .clickToAddCart()
                .clickToCartPage()
                .clickToContinueOrderButton();
        new AuthPageInOrder(webDriver).clickToContinueAsGuest();
    }

    @SneakyThrows
    private void withMoreThanOneGoods(String url, String url1) {
        new ProductPage(webDriver, url).clickToAddCart();
        new ProductPage(webDriver, url1)
                .clickToAddCart()
                .clickToCartPage()
                .clickToButtonDelivery()
                .clickToContinueOrderButton();
        new AuthPageInOrder(webDriver).clickToContinueAsGuest();
    }

    @Test(dataProvider = "dataOneGood")
    public void withOneItemChoose(String url, String text1) {
        withOneGoods(url);
        new OrderPurchase(webDriver)
                .clickToButtonDelivery()
                .setDataToFieldDeliveryAppart("00")
                .setDataToFieldDeliveryStreet("Нулевая улица")
                .setDataToFieldDeliveryHouse("00")
                .payCash()
                .setDataToFieldEmail("aaaaa@mail.ru")
                .setDataToFieldPhone("9099099999")
                .setDataToFieldName("Иван Иванов");
    }

    @Test(dataProvider = "dataMultiGoods")
    public void withMoreItemChoose(String url, String url1, String text1) {
        withMoreThanOneGoods(url, url1);
        new OrderPurchase(webDriver)
                .setDataToFieldDeliveryAppart("00")
                .setDataToFieldDeliveryStreet("Нулевая улица")
                .setDataToFieldDeliveryHouse("00")
                .payCash()
                .setDataToFieldEmail("aaaaa@mail.ru")
                .setDataToFieldPhone("9099099999")
                .setDataToFieldName("Иван Иванов");
    }

    @Test(dataProvider = "dataMultiGoods")
    public void withMoreThanOneItemNotCorrectCity(String url, String url1, String expectedText) {
        withMoreThanOneGoods(url, url1);
        String textFromLabelEmail = new OrderPurchase(webDriver)
                .setDataToFieldDeliveryCity("Неправильный город")
                .setDataToFieldDeliveryAppart("00")
                .setDataToFieldDeliveryStreet("Нулевая улица")
                .setDataToFieldDeliveryHouse("00")
                .payCash()
                .setDataToFieldEmail("aaaaa@mail.ru")
                .setDataToFieldPhone("9099099999")
                .setDataToFieldName("Иван Иванов")
                .getTextFromLabelEmail();
        AssertJUnit.assertEquals("City label invalid", expectedText, textFromLabelEmail);
    }


}
