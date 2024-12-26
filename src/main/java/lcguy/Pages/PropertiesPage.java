package lcguy.Pages;

import lcguy.base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PropertiesPage extends base {
    By list_filters = By.xpath("//ul[@class='properties-filter']//a");
    By list_properties = By.xpath("//div[@class='item' and not(parent::*[contains(@style, 'display: none')])]");

    public void filterSelect(String filterName) {
        scroll_Element(list_filters);
        List<WebElement> filters = driver.findElements(list_filters);
        for (WebElement e : filters){
            if(e.getText().toLowerCase().contains(filterName.toLowerCase())){
                e.click();
                sleep(1000);
                return;
            }
        }
        allure_FailLog("TEST FAIL: filter " + filterName + " not found!");
    }

    public String getName(int i){
        scroll_Element(list_properties);
        scroll_XY(0, 200);
        List <WebElement> properties = driver.findElements(list_properties);
        return properties.get(i).findElement(By.xpath("h4")).getText();
    }

    public PropertyDetailsPage select(int i) {
        scroll_Element(list_properties);
        scroll_XY(0, 200);
        List <WebElement> properties = driver.findElements(list_properties);
        properties.get(i).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new PropertyDetailsPage();
    }
}
