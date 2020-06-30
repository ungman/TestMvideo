package io.github.ungman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPurchase extends OwnPage {
    private final WebDriver webDriver;
    @FindBy(css = "#delivery-form-content > div > div:nth-child(4) > button")
    private WebElement chooseShop;
    @FindBy(css = "#payment-form > ul > li:nth-child(1)")
    private WebElement cashButtonChoose;
    @FindBy(css = "#payment-form > ul > li:nth-child(2)")
    private WebElement cardButtonChoose;
    @FindBy(css = "#payment-form > ul > li:nth-child(3)")
    private WebElement creditButtonChoose;
    @FindBy(css = "#payment-form > ul > li:nth-child(4)")
    private WebElement credit0ButtonChoose;
    @FindBy(css = "#promo-checkbox-15930736381641")
    private WebElement promo;
    @FindBy(css = "input#field_8")
    private WebElement fieldEmail;
    @FindBy(css = "input#myPhone")
    private WebElement fieldPhone;
    @FindBy(css="input#field_6")
    private WebElement fieldName;
    @FindBy(css = "input#anotherRecipient")
    private WebElement fieldAnotherRecipient;
    @FindBy(className = "c-cost-line__text")
    private WebElement costGoods;
    @FindBy(css = "#personal-form > div > ul > li:nth-child(1) > div > label.u-error-text")
    private WebElement labelEmailInvalid;
    @FindBy(css = "#personal-form > div > ul > li:nth-child(2) > div > label.u-error-text")
    private WebElement labelPhoneInvalid;
    @FindBy(css = "#store-locator-form > ul > li:nth-child(2) > label")
    private WebElement changeToShopListButton;
    @FindBy(css = "#store-locator-list > ul > li.c-store-list__item.clearfix")
    private List<WebElement> shopList;
    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/form/ul[1]/li[2]")
    private WebElement buttonDelivery;
    @FindBy(css = "#delivery-city")
    private WebElement fieldDeliveryCity;
    @FindBy(css = "#delivery-street")
    private WebElement fieldDeliveryStreet;
    @FindBy(css = "#delivery-house")
    private WebElement fieldDeliveryHouse;
    @FindBy(css = "#delivery-appart")
    private WebElement fieldDeliveryAppart;
    @FindBy(xpath = "//*[@id=\"delivery-form-content\"]/div/div[2]/div[2]/div/div[1]/div[2]")
    private WebElement selectDate;
    @FindBy(xpath = "//div[contains(@class,'drop-box drop-c-dropdown c-custom-scroll c-dropdown-text__select')]/div[1]/ul")
    private WebElement dropdownDataDelivery;
    @FindBy(xpath = "//*[@id=\"delivery-form-content\"]/div/div[2]/div[1]/div[1]/div/label[2]")
    private WebElement labelInvalidCity;

    @FindBy(css = "body > div.drop-box.drop-c-dropdown.c-custom-scroll.c-dropdown-text__select.c-dropdown_wmax-100 _scrollbar")
    private WebElement dropdownDataDeliviry;

    public OrderPurchase(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public OrderPurchase clickToChangeShop() {
        click(chooseShop);
        return this;
    }

    public OrderPurchase clickShopList() {
        click(changeToShopListButton);
        return this;
    }

    public OrderPurchase setShop(int index) {
        if (index < shopList.size()) {
            click(shopList.get(index));
        }
        return this;
    }

    public OrderPurchase setDataToFieldEmail(String text) {
        System.out.println("setDataToFieldEmail");
        setDataToField(fieldEmail, text);
        return this;
    }

    public OrderPurchase setDataToFieldPhone(String text) {
        System.out.println("setDataToFieldPhone");
        setDataToField(fieldPhone, text);
        return this;
    }

    public OrderPurchase setDataToFieldName(String text) {
        System.out.println("setDataToFieldName");
        setDataToField(fieldName, text);
        return this;
    }

    public OrderPurchase setDataToFieldAnotherRecipient(String text) {
        System.out.println("setDataToFieldAnotherRecipient");
        setDataToField(fieldAnotherRecipient, text);
        return this;
    }

    public OrderPurchase payCash() {
        click(cashButtonChoose);
        return this;
    }

    public OrderPurchase payCard() {
        click(cardButtonChoose);
        return this;
    }

    public OrderPurchase payCredit() {
        click(creditButtonChoose);
        return this;
    }

    public OrderPurchase payCredit0() {
        click(credit0ButtonChoose);
        return this;
    }


    public String getTextFromLabelEmail() {
        return labelEmailInvalid.getText();
    }

    public String getTextFromLabelPhone() {
        return labelPhoneInvalid.getText();
    }

    public OrderPurchase clickToButtonDelivery() {
        click(buttonDelivery);
        return this;
    }

    public OrderPurchase setDataToFieldDeliveryCity(String text) {
        System.out.println("setDataToFieldDeliveryCity");
        setDataToField(fieldDeliveryCity, text);
        return this;
    }

    public OrderPurchase setDataToFieldDeliveryStreet(String text) {
        System.out.println("setDataToFieldDeliveryStreet");
        setDataToField(fieldDeliveryStreet, text);
        return this;
    }

    public OrderPurchase setDataToFieldDeliveryHouse(String text) {
        System.out.println("setDataToFieldDeliveryHouse");
        setDataToField(fieldDeliveryHouse, text);
        return this;
    }

    public OrderPurchase setDataToFieldDeliveryAppart(String text) {
        System.out.println("setDataToFieldDeliveryAppart");
        setDataToField(fieldDeliveryAppart, text);
        return this;
    }


    public String getTextFromLabelInvalidCity() {
        return getText(labelInvalidCity);
    }

    public String getFieldNameText() {
        return getTextFromValue(fieldName);
    }

    public String getFieldEmailText() {
        return getTextFromValue(fieldEmail);
    }

    public String getFieldPhoneText() {
        return getTextFromValue(fieldPhone);
    }

    public String getFieldAnotherRecipientText() {
        return getTextFromValue(fieldAnotherRecipient);
    }
}
