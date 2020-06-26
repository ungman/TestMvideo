package io.github.ungman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderPurchase {
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

    @FindBy(id = "field_8")
    private WebElement fieldEmail;
    @FindBy(id = "myPhone")
    private WebElement fieldPhone;
    @FindBy(css = "input#field_6")
    private WebElement fieldName;
    @FindBy(css = "#anotherRecipient")
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
    //    @FindBy(css = "#delivery-form-types > ul.o-checkout-radio.o-checkout__step__group-radio.u-mb-20.o-checkout__delivery > li:nth-child(2)")
    //    @FindBy(css = "#delivery-form-types > ul.o-checkout-radio.o-checkout__step__group-radio.u-mb-20.o-checkout__delivery > li:nth-child(2)")
//    @FindBy(xpath = "//*[@id='delivery-form-types']/ul[1]/li[2]/label")
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
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public OrderPurchase clickToChangeShop() {
        new Actions(webDriver)
                .click(chooseShop)
                .build()
                .perform();
        return this;
    }

    private OrderPurchase initButtonChangeToShopList() {
        return this;
    }

    public OrderPurchase clickShopList() {
        changeToShopListButton.click();
        return this;
    }

    private void initListShop() {
//        this.shopList = webDriver.findElements(By.cssSelector("#store-locator-list > ul > li.c-store-list__item clearfix"));
    }

    public OrderPurchase setShop(int index) {
        if (index < shopList.size()) {
            new Actions(webDriver)
                    .click(shopList.get(index))
                    .build()
                    .perform();
        }
        return this;
    }

    public OrderPurchase setDataToFieldEmail(String text) {
        sendDataToField(fieldEmail, text);
        return this;
    }

    public OrderPurchase setDataToFieldPhone(String text) {
        sendDataToFieldWithoutClear(fieldPhone, text);
        return this;
    }

    public OrderPurchase setDataToFieldName(String text) {
        sendDataToField(fieldName, text);
        return this;
    }

    public OrderPurchase setDataToFieldAnotherRecipient(String text) {

        if (fieldAnotherRecipient == null)
            fieldAnotherRecipient = webDriver.findElement(By.cssSelector("#anotherRecipient"));

        sendDataToField(fieldAnotherRecipient, text);
        return this;
    }

    public OrderPurchase payCash() {
        clickWithActions(cashButtonChoose);
        return this;
    }

    public OrderPurchase payCard() {
        clickWithActions(cardButtonChoose);
        return this;
    }

    public OrderPurchase payCredit() {
        clickWithActions(creditButtonChoose);
        return this;
    }

    public OrderPurchase payCredit0() {
        clickWithActions(credit0ButtonChoose);
        return this;
    }

    private void clickWithActions(WebElement webElement) {
        new Actions(webDriver)
                .moveToElement(webElement)
                .click()
                .build()
                .perform();
    }

    public void iframeSwitch(WebElement webElement) {
        List<WebElement> iframes = webDriver.findElements(By.tagName("iframe"));
        System.out.println("frame" + iframes.size());
        boolean clicked = true;
        for (WebElement iframe : iframes) {
            try {
                webDriver.switchTo().frame(fieldName);
                clickWithActions(webElement);
            } catch (Exception e) {
                clicked = false;
            } finally {
                webDriver.switchTo().defaultContent();
            }
        }
        webDriver.switchTo().defaultContent();
        if (!clicked) {
            try {
                webElement.click();
            } catch (Exception e) {
                clicked = false;
            }
        }
    }

    public String getTextFromLabelEmail() {
        return labelEmailInvalid.getText();
    }

    public String getTextFromLabelPhone() {
        return labelPhoneInvalid.getText();
    }

    public OrderPurchase clickToButtonDelivery() {
        iframeSwitch(buttonDelivery);
        return this;
    }

    public OrderPurchase setDataToFieldDeliveryCity(String text) {
        sendDataToField(fieldDeliveryCity, text);
        return this;
    }

    public OrderPurchase setDataToFieldDeliveryStreet(String text) {
        sendDataToField(fieldDeliveryStreet, text);
        return this;
    }

    public OrderPurchase setDataToFieldDeliveryHouse(String text) {
        sendDataToField(fieldDeliveryHouse, text);
        return this;
    }

    public OrderPurchase setDataToFieldDeliveryAppart(String text) {
        sendDataToField(fieldDeliveryAppart, text);
        return this;
    }

    private void sendDataToField(WebElement el, String text) {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.and(
                        ExpectedConditions.elementToBeClickable(el)
                        , ExpectedConditions.visibilityOf(el)));
        el.clear();
        el.sendKeys(text);
        el.sendKeys(Keys.ENTER);
    }
    private void sendDataToFieldWithoutClear(WebElement el, String text){
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.and(
                        ExpectedConditions.elementToBeClickable(el)
                        , ExpectedConditions.visibilityOf(el)));
        el.sendKeys(text);
        el.sendKeys(Keys.ENTER);
    }

    public String getTextFromLabelInvalidCity() {
        return labelInvalidCity.getText();
    }

    public String getFieldNameText() {
        return fieldName.getAttribute("value");
    }

    public String getFieldEmailText() {
        return fieldEmail.getAttribute("value");
    }

    public String getFieldPhoneText() {
        return fieldPhone.getAttribute("value");
    }

    public String getFieldAnotherRecipientText() {
        return fieldAnotherRecipient.getAttribute("value");
    }
}
