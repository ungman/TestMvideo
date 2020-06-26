package io.github.ungman.tests;

import io.github.ungman.helper.HelperForWorkingList;
import io.github.ungman.helper.WevDriverRunner;
import io.github.ungman.page.CartPage;
import io.github.ungman.page.MainPage;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CheckOrderCost extends WevDriverRunner {

    public static List<String> positionsListMoreThan5000 = new ArrayList<>();
    public static List<String> positionsListLessThan5000 = new ArrayList<>();
    public static List<String> usedGoods = new ArrayList<>();

    @BeforeTest
    @Override
    public void beforeTest() {
        super.beforeTest();
        fillListData();
    }

    @DataProvider(name = "dataMaxPriceAndGoods")
    public static Object[][] dataMaxPriceAndGoods() {
        return new Object[][]{
                {5000, positionsListLessThan5000},
                {7500, positionsListLessThan5000},
                {10000, positionsListLessThan5000},
                {30000, positionsListMoreThan5000},
                {50000, positionsListMoreThan5000},
                {70000, positionsListMoreThan5000}
        };
    }

    @BeforeMethod
    @Override
    public void initMethod() throws Exception {
        super.initMethod();
        usedGoods = new ArrayList<>();
    }

    @Test(dataProvider = "dataMaxPriceAndGoods")
    public void addingUniqueGoodsToCartWhileAllPriceLessThan(Integer price, List<String> positions) {
        int currentPrice = 0;
        do {
            currentPrice += uniqueGoodToCart(positions)
                    .getOrderPrice();
        } while (currentPrice <= price || usedGoods.size() != positions.size());
        System.out.println(currentPrice);
        AssertJUnit.assertTrue("Goods price less than " + price, price < currentPrice);
    }


    @Test(dataProvider = "dataMaxPriceAndGoods")
    public void addingMultiGoodsToCartWhileAllPriceLessThan(Integer price, List<String> positions) {
        int currentPrice = 0;
        do {
            currentPrice += multiGoodToCard(positions)
                    .getOrderPrice();
        } while (currentPrice <= price);
        System.out.println(currentPrice);
        AssertJUnit.assertTrue("Goods price less than " + price, price < currentPrice);
    }

    private CartPage uniqueGoodToCart(List<String> positions) {
        String product = HelperForWorkingList.getNotUsedElement(positions, usedGoods);
        return new MainPage(webDriver).addUniqueGoodToCart(product);
    }


    private CartPage multiGoodToCard(List<String> positions) {
        String product = HelperForWorkingList.getRandomStringFromList(positions);
        if (!usedGoods.contains(product)) {
            usedGoods.add(product);
            return new MainPage(webDriver).addUniqueGoodToCart(product);
        } else {
            return new CartPage(webDriver).addToGoods(product);
        }
    }


    private void fillListData() {
        positionsListLessThan5000.add("Микроволновая печь соло Gorenje MO17E1W");
        positionsListLessThan5000.add("Крышка для посуды в микроволновую печь Plast Team 24,8см (PT1521/МВНАТ-26)");
        positionsListLessThan5000.add("Оперативная память Transcend 8GB JM2666HLB-8G");
        positionsListLessThan5000.add("Внутренний SSD накопитель HP 250GB S700 2.5 (2DP98AA#ABB)");
        positionsListLessThan5000.add("Процессор AMD Athlon 3000G AM4 BOX (YD3000C6FHBOX)");
        positionsListMoreThan5000.add("Процессор AMD Ryzen Threadripper 2950X TR4 BOX w/o Cooler");
        positionsListMoreThan5000.add("Лазерное МФУ HP LaserJet Pro MFP M28a");
        positionsListMoreThan5000.add("Телевизор Samsung UE43RU7410U");
        positionsListMoreThan5000.add("Ноутбук Apple MacBook Pro 13 i5 1,4/8Gb/256SSD SG");
        positionsListMoreThan5000.add("Смартфон Samsung Galaxy S20+ Black (SM-G985F/DS)");
    }

}
