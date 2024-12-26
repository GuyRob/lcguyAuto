import lcguy.ExternalPages.GoogleMapPage;
import lcguy.ExternalPages.YoutubeChannelPage;
import lcguy.Pages.HomePage;
import lcguy.base;
import org.testng.Assert;
import org.testng.annotations.*;

public class P1_Homepage extends base {

    HomePage homepage;

    String youtubeChannel = "lcontrol";
    String bestDealOption = "Penthouse";

    @BeforeMethod
    public void before() {
        initialDriver();
        allure_Log("Loading homepage");
        homepage = new HomePage();
    }

    @AfterMethod
    public void after() {
        allure_Log("Closing driver");
        quitDriver();
    }

    @Test
    public void P1_checkHeader() {
        allure_LogAttachment("Checking if page loads successfully", "P1", "page_load");
        Assert.assertTrue(homepage.isHeaderDisplayed(), "TEST FAIL: Page header not displayed");
    }

    @Test
    public void P2_banner() {
        allure_Log("On banner clicking on right button twice");
        homepage.bannerClickRight();
        homepage.bannerClickRight();
        allure_LogAttachment("Moved to last banner", "P1", "banner_right");
        allure_Log("On banner clicking on left button");
        homepage.bannerClickLeft();
        allure_LogAttachment("Moved to mid banner", "P1", "banner_left");
    }

    @Test
    public void P3_videoView() {
        allure_Log("Scroll to video section");
        scroll_Element(homepage.btn_videoView);
        allure_LogAttachment("Video Section", "P1", "video_section");
        allure_Log("Click on the video and expect to see in channel name: " + youtubeChannel);
        YoutubeChannelPage youtubeChannelPage = homepage.clickVideo();
        allure_LogAttachment("Youtube Channel", "P1", "video_link");
        String actualYoutubeName = youtubeChannelPage.getChannelName();
        Assert.assertTrue(actualYoutubeName.contains(youtubeChannel), "TEST FAIL: The expected channel name not displayed");
    }

    @Test
    public void P4_bestDeal() {
        allure_Log("Scroll to best deal section");
        scroll_Element(homepage.txt_bestDeal);
        allure_LogAttachment("Best Deal Section", "P1", "bestDeal_section");
        allure_Log("Selecting option " + bestDealOption);
        homepage.bestDeal_SelectByName(bestDealOption);
        allure_LogAttachment("Option Selected", "P1", "bestDeal_option");
        allure_Log("Checking if the active tab is as the selected");
        Assert.assertEquals(homepage.bestDeal_GetActiveName(), bestDealOption);
    }

    @Test
    public void P5_officeMap() {
        allure_Log("Getting the office location");
        String officeLocation = homepage.getOfficeLocation();
        allure_LogAttachment("Office Map Location Upper Section", "P1", "map_section_upper");
        allure_Log("Scrolling to office map section");
        scroll_Element(homepage.txt_contactUs);
        allure_LogAttachment("Office Map Section", "P1", "map_section");
        allure_Log("View large map");
        GoogleMapPage googleMapPage = homepage.mapViewLarge();
        allure_LogAttachment("Large Map View", "P1", "map_link");
        allure_Log("Checking if the google map location is the same as office location: " + officeLocation);
        Assert.assertTrue(googleMapPage.getLocationText().contains(officeLocation), "TEST FAIL: Not showing the same location as office");

    }


}
