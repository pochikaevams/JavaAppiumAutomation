package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    public static final String SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]";
    public static final String SEARCH_INPUT = "//*[contains(@text, 'Search…')]";
    public static final String SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    public static final String SEARCH_RESULT_TITLE_AND_DESC_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE_TEXT}']/../*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION_TEXT}']";
    public static final String SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn";
    public static final String SEARCH_RESULTS_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    public static final String SEARCH_EMPTY_RESULTS_ELEMENT = "//*[@text='No results found']";
    public static final String SEARCH_RESULTS_LIST_ELEMENT = "org.wikipedia:id/search_results_list";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    // TEMPLATES METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Canot find and click init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result, substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result, substring " + substring, 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void waitForResultsListsPresent() {
        this.waitForElementPresent(By.id(SEARCH_RESULTS_LIST_ELEMENT), "Search results list is not present", 15);
    }

    public void waitForResultsListsNotPresent() {
        this.waitForElementNotPresent(By.id(SEARCH_RESULTS_LIST_ELEMENT), "Search results list is not present", 15);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULTS_ELEMENT),
                "Cannot find anything by the request ",
                15
        );

        return this.getAmountOfElemets(By.xpath(SEARCH_RESULTS_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULTS_ELEMENT), "Cannot find empty results", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.asserElementNotPresent(By.xpath(SEARCH_RESULTS_ELEMENT), "We supposed not to find any results");
    }

    private static String getResultTitleAndTextSearchElement(String title_text, String description_text) {
        return SEARCH_RESULT_TITLE_AND_DESC_TPL.replace("{TITLE_TEXT}", title_text).replace("{DESCRIPTION_TEXT}", description_text);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultTitleAndTextSearchElement(title, description);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with title " + title +
                " AND description " + description);
    }

}