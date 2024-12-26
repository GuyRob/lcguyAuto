package lcguy;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import lcguy.Pages.ContactPage;
import lcguy.Pages.HomePage;
import lcguy.Pages.PropertiesPage;
import lcguy.Pages.PropertyDetailsPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class base {
    public static DevTools devTools;
    public static WebDriver driver;
    public static Actions actions;

    public String url = "http://lcguy.s3-website.eu-north-1.amazonaws.com/";

    By btn_nav_Contact = By.xpath("//nav[@class='main-nav']//a[contains(text(), 'Contact Us')]");
    By btn_nav_PropertyDetails = By.xpath("//nav[@class='main-nav']//a[contains(text(), 'Details')]");
    By btn_nav_Properties = By.xpath("//nav[@class='main-nav']//a[contains(text(), 'Properties')]");
    By btn_nav_Home = By.xpath("//nav[@class='main-nav']//a[contains(text(), 'Home')]");
    By txt_breadcrumb = By.xpath("//span[@class='breadcrumb']");


    /** Driver: */
    public void initialDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void quitDriver(){
        driver.quit();
    }
    /**General: */

    public void sleep(int time) {
        try {

            Thread.sleep(time);
        } catch (Exception e) {
            allure_FailLog("Exception: " + e);
            System.out.println("Exception: " + e);
        }
    }

    public List<String> switchTab(int tabID) {
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandles);
        driver.switchTo().window(windowHandlesList.get(tabID));
        return windowHandlesList;
    }

    private static void screenShot(String folder, String name) {
        try {
            File screenshotsFolder = new File("src\\ExtFiles\\screenShots\\" + folder);
            if (!screenshotsFolder.exists()) {
                screenshotsFolder.mkdirs(); // Create the folder if it doesn't exist
            }

            File destFile = new File(screenshotsFolder, name + ".png");

            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, destFile);

        } catch (Exception e) {
            System.out.println("ERROR: Screenshot failed - " + e.getMessage());
            allure_FailLog("ERROR: Screenshot failed - " + e.getMessage());
        }
    }

    /** Javascript cmds */

    public void scroll_Element(By eleBy){
        actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(eleBy)).perform();
        sleep(1);
    }

    public void scroll_XY(int x, int y){
        actions = new Actions(driver);
        actions.scrollByAmount(x, y).perform();
    }

    /** ============================
     * Allure:
     ===============================*/
    public void allure_Log(String message) {
        Allure.step(message);
    }

    public static void allure_LogAttachment(String info, String folder, String name) {
        screenShot(folder, name);

        String imagePath = "src\\ExtFiles\\screenShots\\" + folder + "\\" + name + ".png";
        Path imageFilePath = Paths.get(imagePath);
        if (Files.exists(imageFilePath) && Files.isReadable(imageFilePath)) {
            try (InputStream imageStream = new FileInputStream(imageFilePath.toFile())) {
                Allure.addAttachment(info, imageStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ERROR: Image file not found or not readable.");
            allure_FailLog("ERROR: Image file not found or not readable.");
        }
    }

    public static void allure_FailLog(String message) {
        Allure.step(message, Status.FAILED);
    }

    /** Villa Navigation Bar */
    public ContactPage navigateContactUs() {
        driver.findElement(btn_nav_Contact).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new ContactPage();
    }

    public PropertyDetailsPage navigatePropertyDetails() {
        driver.findElement(btn_nav_PropertyDetails).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new PropertyDetailsPage();
    }

    public PropertiesPage navigateProperties(){
        driver.findElement(btn_nav_Properties).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new PropertiesPage();
    }

    public HomePage navigateHome(){
        driver.findElement(btn_nav_Home).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new HomePage();
    }

    public String geBreadcrumbText(){
        return driver.findElement(txt_breadcrumb).getText().toLowerCase();
    }

}
