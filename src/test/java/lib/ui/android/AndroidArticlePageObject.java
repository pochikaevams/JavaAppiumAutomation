package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        String TITLE = "id:org.wikipedia:id/view_page_title_text";
        String FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        String OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"More options\"]";
        String OPTIONS_ADD_TO_MY_LIST = "xpath://*[@text='Add to reading list']";
        String ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        String MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        String MY_LIST_OK_BUTTON = "xpath://*[@text= 'OK']";
        String CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        String SAVED_LIST_NAME_TLP = "xpath://android.widget.TextView[@text='{LIST_NAME}']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
