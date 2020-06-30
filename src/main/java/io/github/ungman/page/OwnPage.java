package io.github.ungman.page;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OwnPage {
    private final WebDriver webDriver;
    public int durationSecond = 10;
    public int sleepMilis = 250;
    private final JavascriptExecutor javascriptExecutor;
    private final WebDriverWait webDriverWait;

    public OwnPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        javascriptExecutor = (JavascriptExecutor) webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(durationSecond), Duration.ofMillis(sleepMilis));
    }

    protected void click(WebElement el) {
        new Actions(webDriver)
                .moveToElement(initWithVisibleAndClickable(el))
                .click()
                .build()
                .perform();
    }

    protected void setDataToField(WebElement el, String text) {
        try {
//            el = initWithVisibleAndClickable(el);
            el = initWithVisibleAndClickable(el);

            el.clear();
            el.sendKeys(text,Keys.ENTER);
//            new Actions(webDriver)
//                    .moveToElement(el)
//                    .click()
//                    .sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END,Keys.ENTER),text)
//                    .build()
//                    .perform();
//        el.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);

        } catch (Exception e) {
            System.out.println("Cant input text in: " + el.toString());
            System.out.println("tag name:" + el.getTagName());
            System.out.println("id: " + el.getAttribute("id"));
            System.out.println("classname: " + el.getAttribute("class"));
            e.printStackTrace();
        }
    }

    protected void setDataToFieldWithoutDelete(WebElement el, CharSequence... text) {
        try {
//            webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(el)));
            el = initWithVisibleAndClickable(el);
            el.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Cant input text in: " + el.toString());
            System.out.println("tag name:" + el.getTagName());
            System.out.println("id: " + el.getAttribute("id"));
            System.out.println("classname: " + el.getAttribute("class"));
            e.printStackTrace();
        }
    }


    protected String getTextFromValue(WebElement el) {
        return webDriverWait.until(new ExpectedCondition<String>() {
            @Nullable
            @Override
            public String apply(@Nullable WebDriver webDriver) {
                String value= el.getAttribute("value");
                if(value==null || value.isEmpty())
                    return null;
                return value;
            }
        });
//        try {
//            el = initWithVisibleAndClickable(el);
//            String valueFromSelenium = el.getAttribute("value");
//            String valueFromJavaScript = getValueFromJs(el);
////            if (valueFromJavaScript == null)
////                return valueFromSelenium;
////            if (valueFromSelenium == null)
////                return valueFromJavaScript;
//            return (valueFromSelenium.isEmpty()) ? valueFromJavaScript : valueFromSelenium;
//        } catch (Exception e) {
//            System.out.println("Dont geted data");
//        }
//        return null;
    }

    protected String getText(WebElement el) {
        return initWithVisible(el).getText();
    }
    protected String getTextWithoutWait(WebElement el) {
        return el.getText();
    }
    protected WebElement initWithVisible(By by) {
        return webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(by)
        );
    }
    protected List<WebElement> initElementsWithVisible(By by){
        return webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
    protected WebElement initWithVisible(WebElement el) {
        return webDriverWait.until(
                ExpectedConditions.visibilityOf(el)
        );
    }
    protected WebElement in(By by) {
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement initWithVisibleAndClickable(By by) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public WebElement initWithVisibleAndClickable(WebElement el) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(el));
    }

    protected void moveToElement(WebElement el) {
        new Actions(webDriver)
                .moveToElement(initWithVisible(el))
                .build()
                .perform();
    }

    private String getValueFromJs(WebElement el) {
        if (el.getAttribute("id") != null)
            return (String) javascriptExecutor.executeScript(String.format("return $('#%s').val();", el.getAttribute("id")));
        return null;
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
