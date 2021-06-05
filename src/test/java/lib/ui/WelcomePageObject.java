package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String STEP_LEARN_MORE_LINK ="id:Learn more about Wikipedia";
    private static final String STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore";
    private static final String STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages";
    private static final String STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected";
    private static final String NEXT_LINK = "id:Next";
    private static final String GET_STARTED_BUTTON = "id:Get started";
    private static final String SKIP = "id:Skip";


    public WelcomePageObject (AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExplore() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "cannot find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "cannot find 'Learn more about data collected' link", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK, "cannot find 'Next' button", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "cannot find 'Get started' button", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find skip button", 10);
    }
}
