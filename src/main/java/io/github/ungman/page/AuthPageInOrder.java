package io.github.ungman.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPageInOrder {
    private final WebDriver webDriver;
    @FindBy(name = "/com/mvideo/userprofiling/GuestCheckoutFormHandler.anonymousCustomer")
    private WebElement buttonContinueAsGuest;

    public AuthPageInOrder(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickToContinueAsGuest() {
        new Actions(webDriver)
                .click(buttonContinueAsGuest)
                .build()
                .perform();
    }

}
