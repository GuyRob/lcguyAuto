package lcguy.Pages;

import lcguy.base;
import org.openqa.selenium.By;

public class SubmitPage extends base {
    By txt_backHome = By.xpath("//a[text()='Go Back to Home']");

    public boolean isPageDisplayed(){
        return driver.findElement(txt_backHome).isDisplayed();
    }

}
