package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        MY_LIST_SAVED_TITLE_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/item_container']//*[@text='{FOLDER_NAME}']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        SEARCH_ARTICLE_BUTTON = "id:org.wikipedia:id/menu_page_search";
        CLOSE_SAVE_OVERLAY = "id:places auth close";
    }

    public iOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
