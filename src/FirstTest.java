import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/vshkolnyi/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5
        );
    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testFindArticleTabSearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search tab title",
                5
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected default title!",
                "Search…",
                article_title
        );
    }

    @Test
    public void testFindSomeResults()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Sunlight",
                "Cannot find search input",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Sunlight']"),
                "Cannot find title 'Sunlight' searching by 'Sunlight'",
                5
        );


        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Sunlight Foundation']"),
                "Cannot find title 'Sunlight Foundation' searching by 'Sunlight'",
                5
        );


        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Sunlight (cleaning product)']"),
                "Cannot find title 'Sunlight (cleaning product)' searching by 'Sunlight'",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testFindSomeResultsWithSameWord()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Test",
                "Cannot find search input",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@index = '0']//*[contains(@text,'Test')]"),
                "Cannot find same text in result searching by 'Test' on line 0",
                5
        );


        waitForElementPresent(
                By.xpath("//*[@index = '1']//*[contains(@text,'Test')]"),
                "Cannot find same text in result searching by 'Test' on line 1",
                5
        );


        waitForElementPresent(
                By.xpath("//*[@index = '2']//*[contains(@text,'Test')]"),
                "Cannot find same text in result searching by 'Test' on line 2",
                5
        );
    }

    @Test
    public void testSwipeArticle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' article in search",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList()
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find search input",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );

            waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot find article title",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot find button to open article options",
                    5
            );

            waitingForElement();

            waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"),
                    "Cannot find option to add article to reading list",
                    5
            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find 'Got it' tip overlay",
                    5
            );

            waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find input to set name of articles folders",
                    5
            );

            String name_of_folder = "Learning Programming";

            waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    name_of_folder,
                    "Cannot put text into articles folder input",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[@text='OK']"),
                    "Cannot press OK button",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot close article, cannot find X link",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                    "Cannot find navigation button to My lists",
                    5
            );

            waitingForElement();

            waitForElementAndClick(
                    By.xpath("//*[@text='"+ name_of_folder + "']"),
                    "Cannot find created folder",
                    5
            );

            swipeElementToLeft(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Cannot find saved article"
            );

            waitForElementNotPresent(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Cannot delete saved article",
                    5
            );


    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin Park discography";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "zxcwartqdfgss";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";

        String empty_result_label = "//*[@text = 'No results found']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request" + search_line
        );
    }

    @Test
    public void testChangeScreenOrintationOnSearchResult() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' topic searching by  " + search_line,
                15
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

     @Test
     public void testCheckSearchArticleInBackground()
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
                );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find search input",
                    5
                );

            waitForElementPresent(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Search Wikipedia' input",
                    5
                );

            driver.runAppInBackground(2);

            waitForElementPresent(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find article after returning from background",
                    5
            );
        }

    @Test
    public void saveBothArticleToMyList()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_article = "Moon";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_article,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Only natural satellite of Earth']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitingForElement();

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folders",
                5
        );

        String name_of_saving_list = "EX-5";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_saving_list,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/menu_page_search"),
                "Cannot find search button",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_article,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Arrival of a spacecraft on the surface of the Moon']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitingForElement();

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_container']//*[@text='" + name_of_saving_list + "']"),
                "Cannot press save list: " + name_of_saving_list,
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );


        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My lists",
                5
        );

        waitingForElement();

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_saving_list + "']"),
                "Cannot find created folder",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='only natural satellite of Earth']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='only natural satellite of Earth']"),
                "Cannot delete saved article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='arrival of a spacecraft on the surface of the Moon']"),
                "Cannot find second article after delete first article",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/view_page_title_text'][@text='Moon landing']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_overflow_menu"),
                "Cannot find overflow menu button",
                5
        );

        waitingForElement();

        waitForElementAndClick(
                By.xpath("//*[@text='Delete list']"),
                "Cannot delete list",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/empty_container"),
                "Saved list aren't empty",
                5
        );


    }

    @Test
    public void testElementTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_title = "World";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_title,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='" + search_title + "']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find element page title"
        );

    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 1);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
      WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
      element.click();
      return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));

    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;

        while (driver.findElements(by).size() == 0){

            if(already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
       WebElement element = waitForElementPresent(
               by,
               error_message,
               10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    protected void waitingForElement(){
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private void assertElementPresent(By by, String error_message)
    {
       String element_title = driver.findElement(by).getText();
        if(element_title == null){
            throw new AssertionError(error_message);
       }
    }

}




