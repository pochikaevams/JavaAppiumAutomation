package lib.tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class CancelSearch extends CoreTestCase {

    @Test
    public void testCancelSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForResultsListsPresent();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForResultsListsNotPresent();
    }
}
