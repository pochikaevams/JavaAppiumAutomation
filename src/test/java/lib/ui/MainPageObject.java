package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {

        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public int waitResultsAndCount(String locator, String error_message, long timeoutInSeconds) {
        waitForElementPresent(locator, error_message, timeoutInSeconds);
        By by = this.getLocatorByString(locator);

        List<WebElement> results = driver.findElements(by);
        return results.size();
    }

    public boolean isResultsContainsText(String locator, String searchingText) {
        By by = this.getLocatorByString(locator);
        List<WebElement> results = driver.findElements(by);

        for (WebElement result : results) {
            if(!result.getAttribute("name").contains(searchingText)) return false;
        }

        return true;
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.getWidth() / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();

    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swipes = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swipes > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swipes;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes) {
        int already_swipes = 0;

        while(!this.isElementLocatedOnTheScreen(locator)) {
            if(already_swipes > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }

            swipeUpQuick();
            ++already_swipes;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();
        int screen_size_by_y =driver.manage().window().getSize().getHeight();

        return element_location_by_y < screen_size_by_y;
    }

    public void swipeElementtoLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(
                locator,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int rigth_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(rigth_x, middle_y);
        action.waitAction(500);

        if(Platform.getInstance().isAndroid()) {
            action.moveTo(left_x, middle_y);
        } else {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(offset_x,0);
        }
        action.release();
        action.perform();
    }

    public int getAmountOfElemets(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void asserElementNotPresent(String locator, String error_message) {

        int amount_of_elements = getAmountOfElemets(locator);
        if(amount_of_elements > 0) {
            String default_message = "An element " + locator + " supported to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public By getLocatorByString(String locator_with_type) {
        String[] exploaded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploaded_locator[0];
        String locator = exploaded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get typ of locator. Locator " + locator_with_type);
        }
    }

    public void clickElementToTheRightUpperCorner(String  locator, String error_message) {
        WebElement element = this.waitForElementPresent(locator + "/..", error_message);

        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(point_to_click_x, point_to_click_y).perform();
    }

}