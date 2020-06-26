package io.github.ungman.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OwnPage {
    private WebDriver webDriver;

    public OwnPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected void click(WebElement el) {
        initWithVisibleAndClickable(el);
        new Actions(webDriver)
                .click(el)
                .build()
                .perform();
    }

    protected void setDataToField(WebElement el, String text) {
        initWithVisibleAndClickable(el);
        el.clear();
        el.sendKeys(text);
        el.sendKeys(Keys.ENTER);
    }

    protected void setDataToFieldWithoutDelete(WebElement el, String text) {
        initWithVisibleAndClickable(el);
        el.sendKeys(text);
        el.sendKeys(Keys.ENTER);
    }

    public void initWithVisibleAndClickable(WebElement el) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(
                ExpectedConditions.and(
                        ExpectedConditions.elementToBeClickable(el),
                        ExpectedConditions.visibilityOf(el)
                )
        );
    }

    protected String getTextFromValue(WebElement el) {
        initWithVisible(el);
        return el.getAttribute("value");
    }

    protected String getText(WebElement el) {
        initWithVisible(el);
        return el.getText();
    }

    protected void initWithVisible(WebElement el) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(
                ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(el)
                )
        );
    }

    protected void moveToElement(WebElement el) {
        new Actions(webDriver)
                .moveToElement(el)
                .build()
                .perform();
    }
    //    private void closeIFrameWithNotification() {
//        WebElement frameNotificationElement = this.webDriver.findElement(By.cssSelector(".flocktory-widget"));
//        if (frameNotificationElement != null) {
//            try {
//                new WebDriverWait(webDriver, Duration.ofSeconds(3), Duration.ofMillis(250))
//                        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNotificationElement));
//                this.webDriver.switchTo()
//                        .frame(frameNotificationElement)
//                        .findElement(By.xpath("//a[@class='close']"))
//                        .click();
//            } catch (Exception exception) {
//                System.out.println("Cant close windows");
//            }
//        }
//        this.webDriver.switchTo().defaultContent();
//    }

//    private void closeIFrameWithNotification () {
//        WebElement frameNotificationElement = this.webDriver.findElement(By.cssSelector(".flocktory-widget"));
//        if (frameNotificationElement != null) {
//            try {
//                new WebDriverWait(webDriver, Duration.ofSeconds(3), Duration.ofMillis(250))
//                        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNotificationElement));
//                this.webDriver.switchTo()
//                        .frame(frameNotificationElement)
//                        .findElement(By.xpath("//a[@class='close']"))
//                        .click();
//            } catch (Exception exception) {
//                System.out.println("Cant close windows");
//            }
//        }
//        this.webDriver.switchTo().defaultContent();
//    }
//public void closeNotification () {
//    try {
//        ((JavascriptExecutor) webDriver).executeScript("window.alert = function() {}; window.prompt = function() {return null}; window.confirm = function() {return true}");
//    } catch (Exception e) {
//        System.out.println("Close popup;");
//    }
//}
//public void iframeSwitch(WebElement webElement) {
//    List<WebElement> iframes = webDriver.findElements(By.tagName("iframe"));
//    System.out.println("frame" + iframes.size());
//    boolean clicked = true;
//    for (WebElement iframe : iframes) {
//        try {
//            webDriver.switchTo().frame(fieldName);
//            click(webElement);
//        } catch (Exception e) {
//            clicked = false;
//        } finally {
//            webDriver.switchTo().defaultContent();
//        }
//    }
//    webDriver.switchTo().defaultContent();
//    if (!clicked) {
//        try {
//            webElement.click();
//        } catch (Exception e) {
//            clicked = false;
//        }
//    }
//}

}
