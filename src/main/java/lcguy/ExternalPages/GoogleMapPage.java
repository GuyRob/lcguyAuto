package lcguy.ExternalPages;

import lcguy.base;
import org.openqa.selenium.By;

public class GoogleMapPage extends base {
    public String getLocationText() {
        By txt_location = By.xpath("//button[@data-item-id='address']//div[text()]");
        return driver.findElement(txt_location).getText();
    }
}
