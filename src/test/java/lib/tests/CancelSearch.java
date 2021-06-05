package lib.tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class CancelSearch extends CoreTestCase {

    @Test
    public void testCancelSearchResults() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForResultsListsPresent();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForResultsListsNotPresent();
    }
}
