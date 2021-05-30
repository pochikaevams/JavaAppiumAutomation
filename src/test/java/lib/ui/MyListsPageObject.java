package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']";
    public static final String ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);

    }

    private static String getSavedArticleXpathByTtile(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);

    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        String article_xpath = getSavedArticleXpathByTtile(article_title);

        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementtoLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDissappearByTitle(article_title);
    }

    public void waitForArticleToDissappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTtile(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Saved article still present with title " + article_title, 15);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTtile(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Cannot find saved article with title " + article_title, 15);
    }
}