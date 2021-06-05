package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT ="xpath://XCUIElementTypeImage[@name='search']/../XCUIElementTypeSearchField";
        SEARCH_CANCEL_BUTTON ="id:Close";

        SEARCH_RESULTS_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULTS_ELEMENT ="xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULTS_LIST_ELEMENT ="xpath://XCUIElementTypeLink[@visible='true']";

        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_TITLE_AND_DESC_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}