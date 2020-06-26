package io.github.ungman.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPageInOrder extends OwnPage {
    
    private final WebDriver webDriver;
    @FindBy(name = "/com/mvideo/userprofiling/GuestCheckoutFormHandler.anonymousCustomer")
    private WebElement buttonContinueAsGuest;

    public AuthPageInOrder(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickToContinueAsGuest() {
        click(buttonContinueAsGuest);
    }

}
