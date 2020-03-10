package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning Programming";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        ;

        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyNewList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUiFactory.get(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(article_title);
        } else {
            myListsPageObject.swipeByArticleToDelete(article_title);
        }
    }

    @Test
    public void testSaveBothArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "EX-5";

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyNewList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeSaveOverlay();
        }

        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();

        if (Platform.getInstance().isIOS()) {
            searchPageObject.clearSearchLineText();
        }

        searchPageObject.typeSearchLine("Moon");
        searchPageObject.clickByArticleWithSubstring("Arrival of a spacecraft on the surface of the Moon");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyNewList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUiFactory.get(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(article_title);
        } else {
            myListsPageObject.swipeByArticleToDelete(article_title);
        }

        searchPageObject.waitForSearchResult("Moon landing");


        if (Platform.getInstance().isAndroid()) {
            searchPageObject.clickByArticleWithSubstring("Moon landing");
            articlePageObject.waitForTitleElement();
            String saved_title_element = articlePageObject.getArticleTitle();

            assertEquals(
                    "Title does not match: 'Moon landing'",
                    "Moon landing",
                    saved_title_element);
        } else {
            String name_saved_title_element = searchPageObject.getAttributeNameFromArticleCell("Moon Landing");

            assertEquals(
                    "Name does not match: 'Moon Landing - Arrival of a spacecraft on the surface of the Moon'",
                    "Moon Landing" + "\n" + "Arrival of a spacecraft on the surface of the Moon",
                    name_saved_title_element);
        }
    }
}