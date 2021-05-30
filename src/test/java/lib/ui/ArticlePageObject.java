package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String TITLE = "org.wikipedia:id/view_page_title_text";
    private static final String FOOTER_ELEMET = "//*[@text='View page in browser']";
    private static final String OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc=\"More options\"]";
    private static final String OPTIONS_ADD_TO_MY_LIST = "//*[@text='Add to reading list']";
    private static final String ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button";
    private static final String MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input";
    private static final String MY_LIST_OK_BUTTON = "//*[@text= 'OK']";
    private static final String CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
    private static final String SAVED_LIST_NAME_TLP = "//android.widget.TextView[@text='{LIST_NAME}']";



    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMET), "Cannot find footer", 20);
    }

    public void addArticleToMyList(String folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button",
                15
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find option to add article to reading list",
                15
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay",
                15
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of article folder",
                15
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                folder,
                "Cannot put text into articles folder input",
                15
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find OK button",
                15
        );
    }

    public void addArticleToExcitingList(String name_of_folder) {
        waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button",
                15
        );

        waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find option to add article to reading list",
                15
        );

        waitForElementAndClick(
                By.xpath(SAVED_LIST_NAME_TLP.replace("{LIST_NAME}", name_of_folder)),
                "Cannot find 'Got it' tip overlay",
                15
        );

    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article",
                15
        );
    }

    private void assertElementPresent(By by, String error_message) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            String default_message = "An element " + by.toString() + " supported to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertTitlePresent() {
        assertElementPresent(By.id(TITLE), "Title text is not present");
    }

    public boolean isPageTitlePresentNow() {
        try {
            driver.findElement(By.id(TITLE));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}