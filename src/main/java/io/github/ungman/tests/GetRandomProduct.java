package io.github.ungman.tests;

import io.github.ungman.helper.FileReaderData;
import io.github.ungman.helper.HelperForWorkingList;
import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.MainPage;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetRandomProduct extends WevDriverRunner {
    private static final String pathToResourcesFolder = System.getProperty("user.dir") + "\\src\\main\\resources\\" + "\\data\\dataRandomProduct.txt";

    @DataProvider
    public static Object[][] testProvider() {
        return new Object[][]{
//                {pathToResourcesFolder, 2},
//                {pathToResourcesFolder, 3},
                {pathToResourcesFolder, 5}
        };
    }

    @Test(dataProvider = "testProvider")
    public void getRandomProduct(String pathToProductList, int countItem) {
        int index = 0;
        Boolean firstLaunch = true;
        while (countItem > index) {
            String product = HelperForWorkingList.getRandomStringFromList(FileReaderData.readFullFile(pathToProductList));
            new MainPage(webDriver)
                    .navigateReload()
                    .maximizeWindow()
                    .addMultiItem(product);
//                    .clickToReturnShop();
            index++;
        }
//        while (webDriver.getCurrentUrl().equalsIgnoreCase(MainPage.getUrl())){
//            System.out.println(webDriver.getCurrentUrl());
//            webDriver.get(MainPage.getUrl());
//        }
        String countItemInBasket = new MainPage(webDriver)
                .navigateReload()
                .getCountItemInCart();
        AssertJUnit.assertEquals("Count item in card not correct", String.valueOf(countItem), countItemInBasket);
    }

}
