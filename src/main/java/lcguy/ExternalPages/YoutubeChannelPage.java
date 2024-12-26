package lcguy.ExternalPages;

import org.openqa.selenium.By;
import lcguy.base;

public class YoutubeChannelPage extends base {

    By txt_channelName = By.xpath("//div[@class='page-header-view-model-wiz__page-header-headline-info']//h1//span[@role='text']");
    public String getChannelName() {
        return base.driver.findElement(txt_channelName).getText();
    }
}
