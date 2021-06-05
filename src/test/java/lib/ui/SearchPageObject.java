package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {
    public static String SEARCH_INIT_ELEMENT;
    protected static String SEARCH_INPUT;
    protected static String SEARCH_CANCEL_BUTTON;
    protected static String SEARCH_RESULTS_ELEMENT;
    protected static String SEARCH_EMPTY_RESULTS_ELEMENT;
    protected static String SEARCH_RESULTS_LIST_ELEMENT;

    //  Templates locators
    protected static String SEARCH_RESULT_BY_SUBSTRING_TPL;
    protected static String SEARCH_RESULT_TITLE_AND_DESC_TPL;


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    // TEMPLATES METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultTitleAndTextSearchElement(String title_text, String description_text) {
        return SEARCH_RESULT_TITLE_AND_DESC_TPL.replace("{TITLE_TEXT}", title_text).replace("{DESCRIPTION_TEXT}", description_text);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultTitleAndTextSearchElement(title, description);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with title " + title +
                " AND description " + description);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Canot find and click init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result, substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result, substring " + substring, 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void waitForResultsListsPresent() {
        this.waitForElementPresent(SEARCH_RESULTS_LIST_ELEMENT, "Search results list is not present", 15);
    }

    public void waitForResultsListsNotPresent() {
        this.waitForElementNotPresent(SEARCH_RESULTS_LIST_ELEMENT, "Search results list is not present", 15);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULTS_ELEMENT,
                "Cannot find anything by the request ",
                15
        );

        return this.getAmountOfElemets(SEARCH_RESULTS_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULTS_ELEMENT, "Cannot find empty results", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.asserElementNotPresent(SEARCH_RESULTS_ELEMENT, "We supposed not to find any results");
    }

    public boolean isSearchResultsContainsString(String searhing_string) {
        return isResultsContainsText(SEARCH_RESULTS_LIST_ELEMENT, searhing_string);
    }

}
