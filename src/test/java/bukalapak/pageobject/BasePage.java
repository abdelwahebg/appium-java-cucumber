package bukalapak.pageobject;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class BasePage {

    protected AndroidDriver<AndroidElement> androidDriver;
    protected IOSDriver<IOSElement> iosDriver;
    protected WebDriver webDriver;
    private int maxSwipeCount = 10;
    private boolean isScreenshotEnabled = false;
    private PointOption pointOption = new PointOption();
    private WaitOptions waitOptions = new WaitOptions();

    public BasePage(AndroidDriver<AndroidElement> androidDriver) {
        this.androidDriver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver, Duration.ofMillis(10000)), this);
    }

    public BasePage(IOSDriver<IOSElement> iosDriver) {
        this.iosDriver = iosDriver;
        PageFactory.initElements(new AppiumFieldDecorator(iosDriver, Duration.ofMillis(10000)), this);
    }

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AppiumFieldDecorator(webDriver, Duration.ofMillis(10000)), this);
    }

    //region Private Find Element Function

    private AndroidElement findElementWithId(String id) {

        AndroidElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = androidDriver.findElementById(id);
                if (isElementPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipeUp();
            }
        }
        return element;

    }

    private List<AndroidElement> findElementsWithId(String id) {

        List<AndroidElement> element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = androidDriver.findElementsById(id);
                if (isElementPresent(element.get(0))) break;
            } catch (NoSuchElementException e) {
                swipeUp();
            }
        }
        return element;

    }

    private WebElement findWebElementWithId(String id) {

        WebElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = webDriver.findElement(By.id(id));
                if (isWebElementPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipeUpWeb();
            }
        }
        return element;

    }

    private List<WebElement> findWebElementsWithId(String id) {

        List<WebElement> element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = webDriver.findElements(By.id(id));
                if (isWebElementPresent(element.get(0))) break;
            } catch (NoSuchElementException e) {
                swipeUpWeb();
            }
        }
        return element;

    }

    private AndroidElement findElementContainsText(String text) {

        AndroidElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {

            try {
                element = androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
                if (isElementPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipeUp();
            }
        }
        return element;

    }

    private List<AndroidElement> findElementsContainsText(String text) {

        List<AndroidElement> element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = androidDriver.findElementsByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
                if (isElementPresent(element.get(0))) break;
            } catch (NoSuchElementException e) {
                swipeUp();
            }
        }
        return element;

    }

    private AndroidElement findElementWithText(String text) {

        AndroidElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = androidDriver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")");
                if (isElementPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipeUp();
            }
        }
        return element;

    }

    private WebElement findWebElementWithXpath(String xpath) {

        WebElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = webDriver.findElement(By.xpath(xpath));
                if (isWebElementPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipeUpWeb();
            }
        }
        return element;

    }

    private List<WebElement> findWebElementsWithXpath(String xpath) {

        List<WebElement> element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = webDriver.findElements(By.xpath(xpath));
                if (isWebElementPresent(element.get(0))) break;
            } catch (NoSuchElementException e) {
                swipeUpWeb();
            }
        }
        return element;

    }

    private List<AndroidElement> findElementsWithText(String text) {

        List<AndroidElement> element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = androidDriver.findElementsByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")");
                if (isElementPresent(element.get(0))) break;
            } catch (NoSuchElementException e) {
                swipeUp();
            }
        }
        return element;

    }

    private AndroidElement findElementByXpath(String xpath) {
        return androidDriver.findElementByXPath(xpath);
    }

    private List<AndroidElement> findElementsByXpath(String xpath) {
        return androidDriver.findElementsByXPath(xpath);
    }

    private AndroidElement findElementByClassName(String className) {
        return androidDriver.findElementByClassName(className);
    }

    private List<AndroidElement> findElementsByClassName(String className) {
        return androidDriver.findElementsByClassName(className);
    }

    private AndroidElement findElementWithIdWithTimeout(String id, int timeOutInSeconds) {
        By by = By.id(id);
        return (AndroidElement) (new WebDriverWait(androidDriver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private AndroidElement findElementWithXpathWithTimeout(String xpath, int timeOutInSeconds) {
        By by = By.xpath(xpath);
        return (AndroidElement) (new WebDriverWait(androidDriver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement findWebElementWithXpathWithTimeout(String xpath, int timeOutInSeconds) {
        By by = By.xpath(xpath);
        return new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private AndroidElement findElementWithClassNameWithTimeout(String className, int timeOutInSeconds) {
        By by = By.className(className);
        return (AndroidElement) (new WebDriverWait(androidDriver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement findWebElementWithClassNameWithTimeout(String className, int timeOutInSeconds) {
        By by = By.className(className);
        return new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private AndroidElement findElementWithTextWithTimeout(String text, int timeOutInSeconds) {
        By by = By.xpath("/*//*[@text=\"" + text + "\"]");
        return (AndroidElement) (new WebDriverWait(androidDriver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private AndroidElement findElementContainsTextWithTimeout(String text, int timeOutInSeconds) {
        By by = By.xpath("/*//*[contains(@text=\"" + text + "\")]");
        return (AndroidElement) (new WebDriverWait(androidDriver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement findWebElementContainsTextWithTimeout(String text, int timeOutInSeconds) {
        By by = By.xpath("//*[contains(text(),'" + text + "')]");
        return new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private AndroidElement findElementByTextWhileSwipeDown(String text) {

        AndroidElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = findElementWithText(text);
                break;
            } catch (NoSuchElementException e) {
                swipeDown();
            }
        }

        if (element == null) System.out.println("Failed to find text : " + text);
        return element;
    }

    private AndroidElement findElementContainsTextWhileSwipeDown(String text) {

        AndroidElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
                if (isElementPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipeDown();
            }
        }

        if (element == null) System.out.println("Failed to find text : " + text);
        return element;

    }

    private AndroidElement findElementByTextWhileSwipeLeft(String text) {

        AndroidElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = findElementWithText(text);
                break;
            } catch (NoSuchElementException e) {
                swipeLeft();
            }
        }

        if (element == null) System.out.println("Failed to find text : " + text);
        return element;
    }

    private AndroidElement findElementContainsTextWhileSwipeLeft(String text) {

        AndroidElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
                if (isElementPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipeLeft();
            }
        }

        if (element == null) System.out.println("Failed to find text : " + text);
        return element;

    }

    private AndroidElement findElementByTextWhileSwipeRight(String text) {

        AndroidElement view = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                view = findElementWithText(text);
                break;
            } catch (NoSuchElementException e) {
                swipeRight();
            }
        }

        if (view == null) System.out.println("Failed to find text : " + text);
        return view;
    }

    private AndroidElement findElementContainsTextWhileSwipeRight(String text) {

        AndroidElement element = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                element = androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
                if (isElementPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipeRight();
            }
        }

        if (element == null) System.out.println("Failed to find text : " + text);
        return element;

    }

    private AndroidElement findViewByTextUsingTranslate(String text) {
        AndroidElement view = null;
        for (int i = 0; i < maxSwipeCount; i++) {
            try {
                view = waitViewInsensitiveCaseTextV4(text);
                break;
            } catch (NoSuchElementException e) {
                swipeUp();
            }
        }

        if (view == null) System.out.println("failed to find text : " + text);
        return view;
    }

    private String findTextFromSiblingTextViewThatContainsText(String text) {
        return androidDriver.findElementByXPath("//android.widget.TextView[contains(@text, '" + text + "')]/following-sibling::android.widget.TextView").getText();
    }

    private String findTextFromSearchPageResultWithIndex(int index) {
        return androidDriver.findElementsByXPath("//android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[@resource-id='com.bukalapak.android:id/tvTitle']").get(index).getText();
    }

    // Private Wait Text (?)

    private AndroidElement waitViewInsensitiveCaseText(String text) {
        try {
            return androidDriver.findElementByXPath("/*//*[translate('" + text + "', 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')]");
        } catch (Exception e) {
            return androidDriver.findElementByXPath("/*//*[contains(@text,translate('" + text + "', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]");
        }
    }

    private AndroidElement waitViewInsensitiveCaseTextV4(String text) {
        try {
            return androidDriver.findElementByXPath("/*//*[contains(@text,translate('" + text + "', 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'))]");
        } catch (Exception e) {
            return androidDriver.findElementByXPath("/*//*[@text=\"" + text + "\"]");
        }
    }

    //endregion

    //region Private Nominal Function

    private int getIntFromRp(String text) {
        if (text.contains("Rp")) {
            return Integer.parseInt(text.substring(text.indexOf("p") + 1).replace(".", "").trim());
        } else {
            return 0;
        }
    }

    private String getRpFromPrice(int money) {
        String moneyString = (String.format("%,d", money)).replace(',', '.');
        return "Rp" + moneyString;
    }

    //endregion

    //region Global Element Getter Function

    public AndroidElement getElementWithId(String id) {
        return findElementWithId(id);
    }

    public List<AndroidElement> getMultipleElementsWithId(String id) {
        return findElementsWithId(id);
    }

    public WebElement getWebElementWithXpath(String xpath) {
        return findWebElementWithXpath(xpath);
    }

    public WebElement getWebElementWithXpathWithTimeOut(String xpath, int timeout) {
        return findWebElementWithXpathWithTimeout(xpath, timeout);
    }

    public AndroidElement getElementWithText(String text) {
        return findElementWithText(text);
    }

    public List<AndroidElement> getMultipleElementsWithText(String text) {
        return findElementsWithText(text);
    }

    public AndroidElement getElementContainsText(String text) {
        return findElementContainsText(text);
    }

    public List<AndroidElement> getMultipleElementsContainsText(String text) {
        return findElementsContainsText(text);
    }

    public AndroidElement getElementWithIdWithTimeout(String id, int timeout) {
        return findElementWithIdWithTimeout(id, timeout);
    }

    public AndroidElement getElementWithTextWithTimeout(String text, int timeout) {
        return findElementWithTextWithTimeout(text, timeout);
    }

    public AndroidElement getElementContainsTextWithTimeout(String text, int timeout) {
        return findElementContainsTextWithTimeout(text, timeout);
    }

    public AndroidElement getElementWithXpathWithTimeout(String xpath, int timeout) {
        return findElementWithXpathWithTimeout(xpath, timeout);
    }

    public AndroidElement getElementWithClassNameWithTimeout(String className, int timeout) {
        return findElementWithClassNameWithTimeout(className, timeout);
    }

    public AndroidElement getElementByTextWhileSwipeDown(String text) {
        return findElementByTextWhileSwipeDown(text);
    }

    public AndroidElement getElementContainsTextWhileSwipeDown(String text) {
        return findElementContainsTextWhileSwipeDown(text);
    }

    public AndroidElement getElementByTextWhileSwipeLeft(String text) {
        return findElementByTextWhileSwipeLeft(text);
    }

    public AndroidElement getElementContainsTextWhileSwipeLeft(String text) {
        return findElementContainsTextWhileSwipeLeft(text);
    }

    public AndroidElement getElementByTextWhileSwipeRight(String text) {
        return findElementByTextWhileSwipeRight(text);
    }

    public AndroidElement getElementContainsTextWhileSwipeRight(String text) {
        return findElementContainsTextWhileSwipeRight(text);
    }

    //endregion

    //region Global Element Checker Function

    public boolean isElementPresent(AndroidElement androidElement) {
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, 3);
            wait.until(ExpectedConditions.visibilityOf(androidElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWebElementPresent(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 3);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementPresentById(String id, int timeOut) {
        By by = By.id(id);
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, timeOut);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementPresentByText(String text, int timeOut) {
        By by = By.xpath("/*//*[@text=\"" + text + "\"]");
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, timeOut);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementPresentContainsText(String text, int timeOut) {
        By by = By.xpath("/*//*[contains(@text=\"" + text + "\")]");
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, timeOut);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //endregion

    //region Global Text Getter Function

    public String getTextFromViewWithId(String id) {
        return findElementWithId(id).getText();
    }

    public String getTextFromViewWithIdWithTimeOut(String id, int timeout) {
        return findElementWithIdWithTimeout(id, timeout).getText();
    }

    public String getTextFromViewByXpath(String xpath) {
        return findElementByXpath(xpath).getText();
    }

    public String getTextFromWebViewByXpath(String xpath) {
        return findWebElementWithXpath(xpath).getText();
    }

    public String getTextFromViewByXpathWithTimeOut(String xpath, int timeout) {
        return findElementWithXpathWithTimeout(xpath, timeout).getText();
    }

    public String getTextFromWebViewByXpathWithTimeOut(String xpath, int timeout) {
        return findWebElementWithXpathWithTimeout(xpath, timeout).getText();
    }

    public String getTextFromViewByClassName(String className) {
        return findElementByClassName(className).getText();
    }

    public String getTextFromViewByClassNameWithTimeOut(String className, int timeout) {
        return findElementWithClassNameWithTimeout(className, timeout).getText();
    }

    public String getTextFromWebViewByClassNameWithTimeOut(String className, int timeout) {
        return findWebElementWithClassNameWithTimeout(className, timeout).getText();
    }

    public String getTextFromSiblingTextViewThatContainsText(String text) {
        findElementContainsText(text);
        return findTextFromSiblingTextViewThatContainsText(text);
    }

    public String getProductNameFromSearchResultWithIndex(int index) {
        return findTextFromSearchPageResultWithIndex(index);
    }

    public String getRpFromNumber(int text) {
        return getRpFromPrice(text);
    }

    public int getNumberFromRp(String text) {
        return getIntFromRp(text);
    }

    //endregion

    //region Global Tap Function

    public void tapViewWithId(String id) {
        findElementWithId(id).click();
    }

    public void tapViewWithIdOnIndex(String id, int index) {
        findElementsWithId(id).get(index).click();
    }

    public void tapViewWithIdAndTimeOut(String id, int timeout) {
        findElementWithIdWithTimeout(id, timeout).click();
    }

    public void tapViewWithText(String text) {
        findElementWithText(text).click();
    }

    public void tapWebViewWithXpath(String xpath) {
        findWebElementWithXpath(xpath).click();
    }

    public void tapWebViewWithId(String id) {
        findWebElementWithId(id).click();
    }

    public void tapWebViewWithText(String text) {
        findWebElementContainsTextWithTimeout(text, 10).click();
    }

    public void tapViewWithTextOnIndex(String text, int index) {
        findElementsWithText(text).get(index).click();
    }

    public void tapViewWithTextAndTimeOut(String text, int timeout) {
        findElementWithTextWithTimeout(text, timeout).click();
    }

    public void tapViewContainsText(String text) {
        findElementContainsText(text).click();
    }

    public void tapViewContainsTextWhileSwipeDown(String text) {
        findElementContainsTextWhileSwipeDown(text).click();
    }

    public void tapViewContainsTextWhileSwipeLeft(String text) {
        findElementContainsTextWhileSwipeLeft(text).click();
    }

    public void tapViewContainsTextWhileSwipeRight(String text) {
        findElementContainsTextWhileSwipeRight(text).click();
    }


    public void tapViewContainsTextOnIndex(String text, int index) {
        findElementsContainsText(text).get(index).click();
    }

    public void tapViewContainsTextAndTimeOut(String text, int timeout) {
        findElementContainsTextWithTimeout(text, timeout).click();
    }

    public void tapViewByPackageIdAndTimeOut(String id, int timeout) {
        findElementWithIdWithTimeout(id, timeout).click();
    }

    //endregion

    //region Global Type Function

    public void typeTextOnViewWithId(String id, String text) {
        findElementWithId(id).clear();
        findElementWithId(id).sendKeys(text);
    }

    public void typeTextOnViewWithIdAndEnter(String id, String text) {
        findElementWithId(id).clear();
        findElementWithId(id).sendKeys(text);
        pressEnterButton();
    }

    public void typeTextOnViewWithIdOnIndex(String id, int index, String text) {
        List<AndroidElement> fields = findElementsWithId(id);
        fields.get(index).clear();
        fields.get(index).setValue(text);
    }

    public void typeTextOnViewWithIdOnIndexAndEnter(String id, int index, String text) {
        List<AndroidElement> fields = findElementsWithId(id);
        fields.get(index).clear();
        fields.get(index).setValue(text);
        pressEnterButton();
    }

    public void typeTextOnWebViewWithId(String id, String text) {
        findWebElementWithId(id).clear();
        findWebElementWithId(id).sendKeys(text);
    }

    public void typeTextOnWebViewWithXpath(String xpath, String text) {
        findWebElementWithXpath(xpath).clear();
        findWebElementWithXpath(xpath).sendKeys(text);
    }

    //endregion

    //region Global Spinner Function

    public void chooseItemInsideSpinnerWithId(String id, String item) {
        tapViewWithId(id);
        findElementWithText(item).click();
    }

    //endregion

    //region Global Keyboard Function

    public void pressEnterButton() {
        androidDriver.pressKeyCode(AndroidKeyCode.ENTER);
    }

    public void pressBackButton() {
        androidDriver.pressKeyCode(AndroidKeyCode.BACK);
    }

    public void hideKeyboard() {
        try {
            androidDriver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Keyboard already hide");
        }
    }

    //endregion

    //region Global Screenshot Function

    public void takeScreenshot(String screenshotName) {
        if (isScreenshotEnabled) {
            delay(500);
            File srcFile = androidDriver.getScreenshotAs(OutputType.FILE);
            File targetFile = new File("/Users/bukalapak/Documents/Screenshot/" + screenshotName + ".jpg");
            try {
                FileUtils.copyFile(srcFile, targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //endregion

    //region Global Checker Function

    public void checkShowCaseButton() {
        if (isElementPresentById("button_showcase", 2)) {
            try {
                tapViewWithId("button_showcase");
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkPageHeaderText(String text) {
        return isElementPresentByText(text, 10);
    }

    //endregion

    //region Global Navigation Function

    public void goToUrl(String url) {
        webDriver.navigate().to(url);
        delay(1000);
    }

    public void goBack() {
        webDriver.navigate().back();
    }

    public void goForward() {
        webDriver.navigate().forward();
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }

    public void swipeUp() {

        Dimension size = androidDriver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(androidDriver);

        int y0 = (int) (size.height * 0.7);
        int y1 = (int) (size.height * 0.3);
        int x = (size.width / 2);

        touchAction
                .press(pointOption.withCoordinates(x, y0))
                .waitAction(waitOptions.withDuration(Duration.ofMillis(1250)))
                .moveTo(pointOption.withCoordinates(x, y1))
                .release()
                .perform();
    }

    public void swipeUpWeb() {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("window.scrollBy(0,300)", "");
    }

    public void swipeUp(float yStart, float yEnd) {

        Dimension size = androidDriver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(androidDriver);

        int y0 = (int) (size.height * yStart);
        int y1 = (int) (size.height * yEnd);
        int x = (size.width / 2);

        touchAction
                .press(pointOption.withCoordinates(x, y0))
                .waitAction(waitOptions.withDuration(Duration.ofMillis(1250)))
                .moveTo(pointOption.withCoordinates(x, y1))
                .release()
                .perform();
    }

    public void swipeDown() {

        Dimension size = androidDriver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(androidDriver);

        int y0 = (int) (size.height * 0.7);
        int y1 = (int) (size.height * 0.3);
        int x = (size.width / 2);

        touchAction
                .press(pointOption.withCoordinates(x, y1))
                .waitAction(waitOptions.withDuration(Duration.ofMillis(1250)))
                .moveTo(pointOption.withCoordinates(x, y0))
                .release()
                .perform();

    }

    public void swipeDownWeb() {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("window.scrollBy(0,-300)", "");
    }

    public void swipeDown(float yStart, float yEnd) {

        Dimension size = androidDriver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(androidDriver);

        int y0 = (int) (size.height * yEnd);
        int y1 = (int) (size.height * yStart);
        int x = (size.width / 2);

        touchAction
                .press(pointOption.withCoordinates(x, y1))
                .waitAction(waitOptions.withDuration(Duration.ofMillis(1250)))
                .moveTo(pointOption.withCoordinates(x, y0))
                .release()
                .perform();
    }

    public void swipeLeft() {

        Dimension size = androidDriver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(androidDriver);

        int x0 = (int) (size.width * 0.8);
        int x1 = (int) (size.width * 0.2);
        int y = (size.height / 2);

        touchAction
                .press(pointOption.withCoordinates(x0, y))
                .waitAction(waitOptions.withDuration(Duration.ofMillis(1250)))
                .moveTo(pointOption.withCoordinates(x1, y))
                .release()
                .perform();

    }

    public void swipeLeftWeb() {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("window.scrollBy(300, 0)", "");
    }

    public void swipeLeft(float xStart, float xEnd) {

        Dimension size = androidDriver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(androidDriver);

        int x0 = (int) (size.width * xStart);
        int x1 = (int) (size.width * xEnd);
        int y = (size.height / 2);

        touchAction
                .press(pointOption.withCoordinates(x0, y))
                .waitAction(waitOptions.withDuration(Duration.ofMillis(1250)))
                .moveTo(pointOption.withCoordinates(x1, y))
                .release()
                .perform();

    }

    public void swipeRight() {

        Dimension size = androidDriver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(androidDriver);

        int x0 = (int) (size.width * 0.8);
        int x1 = (int) (size.width * 0.2);
        int y = (size.height / 2);

        touchAction
                .press(pointOption.withCoordinates(x1, y))
                .waitAction(waitOptions.withDuration(Duration.ofMillis(1250)))
                .moveTo(pointOption.withCoordinates(x0, y))
                .release()
                .perform();

    }

    public void swipeRightWeb() {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("window.scrollBy(-300, 0)", "");
    }

    public void swipeRight(float xStart, float xEnd) {

        Dimension size = androidDriver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(androidDriver);

        int x0 = (int) (size.width * xEnd);
        int x1 = (int) (size.width * xStart);
        int y = (size.height / 2);

        touchAction
                .press(pointOption.withCoordinates(x1, y))
                .waitAction(waitOptions.withDuration(Duration.ofMillis(1250)))
                .moveTo(pointOption.withCoordinates(x0, y))
                .release()
                .perform();

    }

    public void goToHomeTab() {
        for (int i = 0; i < 5; i++) {
            try {
                tapViewWithId("bottombartab_home");
                break;
            } catch (Exception e) {
                pressBackButton();
            }
        }
    }

    public void goToTransaksiTab() {
        for (int i = 0; i < 5; i++) {
            try {
                tapViewWithId("bottombartab_transaction");
                break;
            } catch (Exception e) {
                pressBackButton();
            }
        }
    }

    public void goToFavoriteTab() {
        for (int i = 0; i < 5; i++) {
            try {
                tapViewWithId("bottombartab_favorite");
                break;
            } catch (Exception e) {
                pressBackButton();
            }
        }
    }

    public void goToCartTab() {
        for (int i = 0; i < 5; i++) {
            try {
                tapViewWithId("bottombartab_cart");
                break;
            } catch (Exception e) {
                pressBackButton();
            }
        }
    }

    public void goToProfileTab() {
        for (int i = 0; i < 5; i++) {
            try {
                tapViewWithId("bottombartab_profile");
                break;
            } catch (Exception e) {
                pressBackButton();
            }
        }
    }

    public void goToChatTab() {
        for (int i = 0; i < 5; i++) {
            try {
                tapViewWithId("bottombartab_chat");
                break;
            } catch (Exception e) {
                pressBackButton();
            }
        }
    }

    //endregion

    //region Global Dialog Function

    public void chooseConfirmDialog(boolean isOk) {
        if (isOk) {
            tapViewWithIdAndTimeOut("android:id/button1", 10);
        } else {
            tapViewWithIdAndTimeOut("android:id/button2", 10);
        }
    }

    //endregion

    //region Global Wait Function

    public void waitUntilWebElementDisplayedByXpath(String xpath, int timeout) {
        findWebElementWithXpathWithTimeout(xpath, timeout).isDisplayed();
    }

    public void delay(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitProgressBar() {
        for (int i = 0; i < 5; i++) {
            delay(1000);
            try {
                AndroidElement progressBar = findElementWithId("progressbar_loading_image");
                System.out.println("loading...");
            } catch (NoSuchElementException e) {
                break; //finished loading
            }
        }
    }

    public void waitLoadProcess(String loadProcessId) {
        for (int i = 0; i < 60; i++) {
            delay(1000);
            try {
                AndroidElement progressBar = findElementWithId(loadProcessId);
                System.out.println("loading...");
            } catch (NoSuchElementException e) {
                break; //finished loading
            }
        }
    }

    //endregion

    //region Global Hard Refresh

    public void doHardRefresh(int refreshCount) {
        for (int i = 1; i <= refreshCount; i++) {
            delay(2000);
            swipeDown();
            delay(2000);
        }
    }

    //endregion

    //region Global Image Function

    public void captureImageFromCamera() {
        try {
            tapViewByPackageIdAndTimeOut("com.android.camera:id/shutter_button", 10);
            tapViewByPackageIdAndTimeOut("com.android.camera:id/done_button", 10);
        } catch (Exception e) {
            tapViewByPackageIdAndTimeOut("com.android.camera2:id/shutter_button", 10);
            tapViewByPackageIdAndTimeOut("com.android.camera2:id/done_button", 10);
        }
    }

    public void selectImageThumbnail() {
        findElementByClassName("android.widget.ImageView").click();
    }

    //endregion

    //region Global utility

    public String getRandom(String[] array) {
        int random = new Random().nextInt(array.length);
        return array[random];
    }

    public void setDriverLocation(double latitude, double longitude, double altitude) {
        Location location = new Location(latitude, longitude, altitude);
        androidDriver.setLocation(location);
    }

    //endregion
}
