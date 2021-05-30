package lib.tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex5Refactored extends CoreTestCase {


    @Test
    public void testEx5() {


        String folder = "Programming";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToMyList(folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Programming language");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToExcitingList(folder);
        ArticlePageObject.closeArticle();


        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(folder);

        MyListsPageObject.waitForArticleToDissappearByTitle("Java (programming language)");
        MyListsPageObject.waitForArticleToAppearByTitle("JavaScript");
    }
}
