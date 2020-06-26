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
                {"Иванов Иван", "9099099999", "email@email.com"}
        };
    }

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
                .clickToCartPage();
        new ProductPage(webDriver, url1)
                .navigate()
                .clickToAddCart()
                .clickToCartPage()
                .clickToContinueOrderButton()
                .clickToContinueAsGuest();
    }

    @Test(dataProvider = "dataPerson")
    public void buyGoodsByGuestWithCashWithFirstShopOnList(String name, String phone, String email)  {
        final OrderPurchase orderPurchase = new OrderPurchase(webDriver)
                .payCash()
                .clickToChangeShop()
                .clickShopList()
                .setShop(1)
                .setDataToFieldEmail(email)
                .setDataToFieldPhone(phone)
                .setDataToFieldName(name);
        AssertJUnit.assertEquals("Field name data not Correct", name, orderPurchase.getFieldNameText());
        AssertJUnit.assertEquals("Field email data not Correct", email, orderPurchase.getFieldEmailText());
        AssertJUnit.assertEquals("Field phone data not Correct", "+7" + phone, orderPurchase.getFieldPhoneText().replace(" ", ""));
    }


    @Test(dataProvider = "dataPerson")
    public void buyGoodsByGuestWithCard(String name, String phone, String email) {
        OrderPurchase orderPurchase = new OrderPurchase(webDriver)
                .payCard()
                .setDataToFieldEmail(email)
                .setDataToFieldPhone(phone)
                .setDataToFieldName(name)
                .setDataToFieldAnotherRecipient(name);

        AssertJUnit.assertEquals("Field name data not Correct", name, orderPurchase.getFieldNameText());
        AssertJUnit.assertEquals("Field email data not Correct", email, orderPurchase.getFieldEmailText());
        AssertJUnit.assertEquals("Field AnotherRecipient data not Correct", name, orderPurchase.getFieldAnotherRecipientText());

    }


    @Test
    public void tryWithUncorrectEmail() {
        String textFromLabelEmail = new OrderPurchase(webDriver)
                .payCash()
                .setDataToFieldEmail("11111")
                .setDataToFieldName("123")
                .getTextFromLabelEmail();
        String expectedText = "Email указан в неверном формате";
        AssertJUnit.assertEquals("Label text not valid", expectedText, textFromLabelEmail);
    }


    @Test
    public void tryWithUncorrectPhone() {
        String textFromLabelPhone = new OrderPurchase(webDriver)
                .payCash()
                .setDataToFieldPhone("11111")
                .setDataToFieldName("123")
                .getTextFromLabelPhone();
        String expectedText = "Телефон указан в неверном формате";
        AssertJUnit.assertEquals("Label text not valid", expectedText, textFromLabelPhone);
    }

}
