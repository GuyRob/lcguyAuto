package lcguy.Pages;

import lcguy.base;
import org.openqa.selenium.By;

public class PropertyDetailsPage extends base {
    By txt_name = By.xpath("//div[@class='main-content']//h4");


    public String getName() {
        scroll_Element(txt_name);
        return driver.findElement(txt_name).getText();
    }
}
