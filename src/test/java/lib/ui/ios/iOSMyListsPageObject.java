package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        ARTICLE_COLLECTION_VIEW_CELL = "xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}