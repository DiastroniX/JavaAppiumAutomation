package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning Programming";

        articlePageObject.addArticleToMyNewList(name_of_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);

    }

    @Test
    public void testSaveBothArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Moon");
        searchPageObject.clickByArticleWithSubstring("Only natural satellite of Earth");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "EX-5";

        articlePageObject.addArticleToMyNewList(name_of_folder);
        articlePageObject.clickSearchButtonOnArticle();

        searchPageObject.typeSearchLine("Moon");
        searchPageObject.clickByArticleWithSubstring("Arrival of a spacecraft on the surface of the Moon");

        articlePageObject.waitForTitleElement();

        String article_title_2 = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMySavedList(name_of_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title_2);
        myListsPageObject.openSavedArticleInMyLists(article_title);

        articlePageObject.getArticleTitle();

        String article_page_title = articlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected default title!",
                "Moon",
                article_page_title
        );
    }
}
