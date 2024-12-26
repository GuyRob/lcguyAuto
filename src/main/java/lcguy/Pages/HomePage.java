package lcguy.Pages;

import lcguy.ExternalPages.GoogleMapPage;
import lcguy.ExternalPages.YoutubeChannelPage;
import lcguy.base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends base {

    By txt_mainNav = By.xpath("//nav[@class='main-nav']");
    By btn_bannerRight = By.xpath("//i[@class='fa fa-angle-right']");
    By btn_bannerLeft = By.xpath("//i[@class='fa fa-angle-left']");
    public By btn_videoView = By.xpath("//i[@class='fa fa-play']");
    public By txt_bestDeal = By.xpath("//div[@class='section best-deal']//div[@class='section-heading']");
    By list_bestDealOptions = By.xpath("//div[@class='section best-deal']//li//button");
    By btn_mapView = By.xpath("//div[@id='mapDiv']//div[@class='google-maps-link']");
    By txt_officeLocation = By.xpath("//div[@class='sub-header']//li[i[@class='fa fa-map']]");
    public By txt_contactUs = By.xpath("//div[@class='contact section']");
    By inp_form_name = By.id("name");
    By inp_form_email = By.id("email");
    By inp_form_subject = By.id("subject");
    By inp_form_message = By.id("message");
    By btn_form_submit = By.id("form-submit");

    public boolean isHeaderDisplayed() {
        return driver.findElement(txt_mainNav).isDisplayed();
    }


    public void bannerClickRight() {
        scroll_Element(btn_bannerRight);
        driver.findElement(btn_bannerRight).click();
        sleep(1000); // wait animation
    }

    public void bannerClickLeft() {
        scroll_Element(btn_bannerLeft);
        driver.findElement(btn_bannerLeft).click();
        sleep(1000); // wait animation
    }

    public YoutubeChannelPage clickVideo() {
        driver.findElement(btn_videoView).click();
        switchTab(1);
        sleep(2000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new YoutubeChannelPage();
    }

    public void bestDeal_SelectByName(String bestDealOption) {
        List<WebElement> bestDeals = driver.findElements(list_bestDealOptions);
        for (WebElement e : bestDeals){
            if (e.getText().toLowerCase().contains(bestDealOption.toLowerCase())){
                e.click();
                sleep(1000); // wait animation
                return;
            }
        }
        allure_FailLog("TEST FAIL: Best deal option " + bestDealOption + " not found!");
    }

    public String bestDeal_GetActiveName() {
        List<WebElement> bestDeals = driver.findElements(list_bestDealOptions);
        for (WebElement e : bestDeals){
            if (e.getAttribute("class").contains("active")){
                return e.getText();
            }
        }
        return "TESTFAIL_MissingActive";
    }

    public GoogleMapPage mapViewLarge() {
        WebElement firstIframe = driver.findElement(By.xpath("//iframe[1]"));
        driver.switchTo().frame(firstIframe);
        sleep(1000); // load switch frame
        scroll_Element(btn_mapView);
        driver.findElement(btn_mapView).click();
        switchTab(1);
        sleep(4000); // load google maps
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new GoogleMapPage();
    }

    public String getOfficeLocation() {
        scroll_Element(txt_officeLocation);
        return driver.findElement(txt_officeLocation).getText();
    }


    public void form_sendName(String fullName) {
        driver.findElement(inp_form_name).clear();
        driver.findElement(inp_form_name).sendKeys(fullName);
    }

    public void form_sendEmail(String email) {
        driver.findElement(inp_form_email).clear();
        driver.findElement(inp_form_email).sendKeys(email);
    }

    public void form_sendSubject(String subject) {
        driver.findElement(inp_form_subject).clear();
        driver.findElement(inp_form_subject).sendKeys(subject);
    }

    public void form_sendMessage(String message) {
        driver.findElement(inp_form_message).clear();
        driver.findElement(inp_form_message).sendKeys(message);
    }

    public SubmitPage form_submit() {
        scroll_Element(btn_form_submit);
        driver.findElement(btn_form_submit).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        return new SubmitPage();
    }
}
