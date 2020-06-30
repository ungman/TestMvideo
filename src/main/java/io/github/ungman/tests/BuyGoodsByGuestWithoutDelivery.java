package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.OrderPurchase;
import io.github.ungman.page.ProductPage;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BuyGoodsByGuestWithoutDelivery extends WevDriverRunner {

    @DataProvider
    public static Object[][] dataPerson() {
        return new Object[][]{
                {"Иванов Иван", "9099099999", "email@mail.com"}
        };
    }

    private OrderPurchase orderPurchase;

    @BeforeMethod
    @Override
    public void initMethod() throws Exception {
        super.initMethod();
        preCondition();
    }

    private void preCondition() {
        String url = "https://www.mvideo.ru/products/fitnes-treker-xiaomi-mi-band-3-black-xmsh05hm-30040243";
        String url1 = "https://www.mvideo.ru/products/smennyi-remeshok-dlya-nosimogo-ustroistva-mi-band-3-4-strap-orange-30046388";
        new ProductPage(webDriver, url)
                .navigate()
                .clickToAddCart()
                .clickToCartPage()
                .clickToReturnShop();
        orderPurchase = new ProductPage(webDriver, url1)
                .navigate()
                .clickToAddCart()
                .clickToCartPage()
                .clickToContinueOrderButton()
                .clickToContinueAsGuest();
    }

    @Test(dataProvider = "dataPerson")
    public void buyGoodsByGuestWithCashWithFirstShopOnList(String name, String phone, String email) {
        orderPurchase
                .payCash()
                .clickToChangeShop()
                .clickShopList()
                .setShop(1)
                .setDataToFieldEmail(email)
                .setDataToFieldPhone(phone)
                .setDataToFieldName(name);
        String nameActual = orderPurchase.getFieldNameText();
        String emailActual = orderPurchase.getFieldEmailText();
//        AssertJUnit.assertEquals("Field email data not Correct", email, emailActual);
        AssertJUnit.assertEquals("Field name data not Correct", name, nameActual);
//        AssertJUnit.assertEquals("Field phone data not Correct", phone, orderPurchase.getFieldPhoneText().replace(" ", ""));
    }


    @Test(dataProvider = "dataPerson")
    public void fgbuyGoodsByGuestWithCard(String name, String phone, String email) {
        orderPurchase
                .payCard()
                .setDataToFieldEmail(email)
                .setDataToFieldPhone(phone)
                .setDataToFieldName(name)
                .setDataToFieldAnotherRecipient(name);
        String nameActual = orderPurchase.getFieldNameText();
        String emailActual = orderPurchase.getFieldEmailText();
        String anotherRecipientActual = orderPurchase.getFieldAnotherRecipientText();

        AssertJUnit.assertEquals("Field name data not Correct", name, nameActual);
//        AssertJUnit.assertEquals("Field email data not Correct", email, emailActual);
//        AssertJUnit.assertEquals("Field AnotherRecipient data not Correct", name, anotherRecipientActual);
//
    }


    @Test
    public void tryWithUncorrectEmail() {
        String textFromLabelEmail = orderPurchase
                .payCash()
                .setDataToFieldEmail("11111")
                .setDataToFieldName("123")
                .getTextFromLabelEmail();
        String expectedText = "Email указан в неверном формате";
        AssertJUnit.assertEquals("Label text not valid", expectedText, textFromLabelEmail);
    }


    @Test
    public void tryWithUncorrectPhone() {
        String textFromLabelPhone = orderPurchase
                .payCash()
                .setDataToFieldPhone("11111")
                .setDataToFieldName("123")
                .getTextFromLabelPhone();
        String expectedText = "Телефон указан в неверном формате";
        AssertJUnit.assertEquals("Label text not valid", expectedText, textFromLabelPhone);
    }


}
