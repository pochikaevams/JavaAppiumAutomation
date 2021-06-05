import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex9 extends CoreTestCase {

    @Test
    public void Ex9Work() {

        String folder = "Learning programming";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        SearchPageObject.waitForElementByTitleAndDescription(
                "Java",
                "Island of Indonesia"
        );

        SearchPageObject.waitForElementByTitleAndDescription(
                "Java (programming language)",
                "Object-oriented programming language"
        );

        SearchPageObject.waitForElementByTitleAndDescription(
                "JavaScript",
                "Programming language"
        );
    }
}