package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.AuthPageInOrder;
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
                        "Мы не знаем так    ой город. Проверьте его написание или укажите другой."
                }
        };
    }

    @SneakyThrows
    public OrderPurchase withOneGoods(String url) {
        return new ProductPage(webDriver, url)
                .navigate()
                .clickToAddCart()
                .clickToCartPage()
//                .clickToButtonDelivery()
                .clickToContinueOrderButton()
                .clickToContinueAsGuest();
    }

    @SneakyThrows
    private OrderPurchase withMoreThanOneGoods(String url, String url1) {
        new ProductPage(webDriver, url).
                navigate()
                .clickToAddCart()
                .clickReturnToShop();
        return new ProductPage(webDriver, url1)
                .navigate()
                .clickToAddCart()
                .clickToCartPage()
                .clickToButtonDelivery()
                .clickToContinueOrderButton()
                .clickToContinueAsGuest();
    }

    @Test(dataProvider = "dataOneGood")
    public void withOneItemChoose(String url, String text1) {
        withOneGoods(url)
                .clickToButtonDelivery()
                .setDataToFieldDeliveryStreet("Нулевая улица")
                .setDataToFieldDeliveryHouse("07")
                .setDataToFieldDeliveryAppart("00")
                .payCash()
                .setDataToFieldName("Иван Иванов")
                .setDataToFieldPhone("9099099999")
                .setDataToFieldEmail("aaaaa@mail.ru")
        ;
    }

    @Test(dataProvider = "dataMultiGoods")
    public void withMoreItemChoose(String url, String url1, String text1) {
        withMoreThanOneGoods(url, url1)
                .setDataToFieldDeliveryStreet("Нулевая улица")
                .setDataToFieldDeliveryHouse("07")
                .setDataToFieldDeliveryAppart("00")
                .payCash()
                .setDataToFieldName("Иван Иванов")
                .setDataToFieldPhone("9099099999")
                .setDataToFieldEmail("aaaaa@mail.ru")
        ;
    }

    @Test(dataProvider = "dataMultiGoods")
    public void withMoreThanOneItemNotCorrectCity(String url, String url1, String expectedText) {
        String textFromLabelEmail = withMoreThanOneGoods(url, url1)
                .setDataToFieldDeliveryCity("Неправильный город")
                .setDataToFieldDeliveryStreet("Нулевая улица")
                .setDataToFieldDeliveryHouse("07")
                .setDataToFieldDeliveryAppart("00")
                .payCash()
                .setDataToFieldName("Иван Иванов")
                .setDataToFieldPhone("9099099999")
                .setDataToFieldEmail("aaaaa@mail.ru")
                .getFieldEmailText();
        AssertJUnit.assertEquals("Email not correct", "aaaaa@mail.ru", textFromLabelEmail);
    }


}
