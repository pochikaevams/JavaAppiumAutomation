package lib.tests;

import io.appium.java_client.AppiumDriver;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class Ex12 extends CoreTestCase {

    @Test
    public void testEx12() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        Assert.assertTrue(SearchPageObject.isSearchResultsContainsString("Java"));
        SearchPageObject.clickCancelSearch();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Firefox");
        SearchPageObject.waitForSearchResult("Web browser developed by the Mozilla Foundation");
        Assert.assertTrue(SearchPageObject.isSearchResultsContainsString("Firefox"));
        SearchPageObject.clickCancelSearch();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Google");
        SearchPageObject.waitForSearchResult("Web browser developed by Google");
        Assert.assertTrue(SearchPageObject.isSearchResultsContainsString("Google"));
    }
}
