package lib.ui;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    public static String TITLE;
    protected static String FOOTER_ELEMENT;
    protected static String OPTIONS_BUTTON;
    protected static String OPTIONS_ADD_TO_MY_LIST;
    protected static String ADD_TO_MY_LIST_OVERLAY;
    protected static String MY_LIST_NAME_INPUT;
    protected static String MY_LIST_OK_BUTTON;
    protected static String CLOSE_ARTICLE_BUTTON;
    protected static String SAVED_LIST_NAME_TLP;



    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find footer", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find footer", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button",
                15
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option to add article to reading list",
                15
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                15
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                15
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                15
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find OK button",
                15
        );
    }

    public void addArticleToExcitingList(String name_of_folder) {
        waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button",
                15
        );

        waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option to add article to reading list",
                15
        );

        waitForElementAndClick(
                SAVED_LIST_NAME_TLP.replace("{LIST_NAME}", name_of_folder),
                "Cannot find 'Got it' tip overlay",
                15
        );

    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article",
                15
        );
    }

    private void assertElementPresent(String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            String default_message = "An element " + by.toString() + " supported to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertTitlePresent() {
        assertElementPresent(TITLE, "Title text is not present");
    }

    public boolean isPageTitlePresentNow() {
        try {
            By by = this.getLocatorByString(TITLE);
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void addArticleToMySaved() {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST, "Cannot find option to add article to reading list", 20);
    }
}