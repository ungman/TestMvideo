package io.github.ungman.tests;

import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.AuthPageInOrder;
import io.github.ungman.page.CartPage;
import io.github.ungman.page.OrderPurchase;
import io.github.ungman.page.ProductPage;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BuyGoodsByGuestWithoutDelivery extends WevDriverRunner {

    @BeforeMethod
    @Override
    public void initMethod() throws Exception {
        super.initMethod();
        preCondition();
    }

    private void preCondition() {
        String url = "https://www.mvideo.ru/products/fitnes-treker-xiaomi-mi-band-3-black-xmsh05hm-30040243";
        String url1 = "https://www.mvideo.ru/products/smennyi-remeshok-dlya-nosimogo-ustroistva-mi-band-3-4-strap-orange-30046388";
        new ProductPage(webDriver, url).clickToAddCart();
        new ProductPage(webDriver, url1).clickToAddCart();
        CartPage cartPage = new CartPage(webDriver);
        cartPage.clickToContinueOrderButton();
        new AuthPageInOrder(webDriver).clickToContinueAsGuest();
    }

    @SneakyThrows
    @Test
    public void buyGoodsByGuestWithCash() throws InterruptedException {
        new OrderPurchase(webDriver)
                .clickToChooseShop()
                .clickShopList() /* need */
                .setShop(0) /*need*/
                .payCash()
                .setDataToFieldEmail("aaaaa@mail.ru")
                .setDataToFieldPhone("9099099999")
                .setDataToFieldName("Иван Иванов");
        Thread.sleep(5000);
    }

    @SneakyThrows
    @Test
    public void buyGoodsByGuestWithCard() {
        new OrderPurchase(webDriver)
                .payCard()
                .setDataToFieldEmail("aaaaa@mail.ru")
                .setDataToFieldPhone("9099099999")
                .setDataToFieldName("Иван Иванов")
                .setDataToFieldAnotherRecipient("Иван Иванов Иванович");
            Thread.sleep(5000);
    }


}
