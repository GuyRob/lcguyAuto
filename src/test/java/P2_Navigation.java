import lcguy.Pages.HomePage;
import lcguy.Pages.PropertiesPage;
import lcguy.Pages.PropertyDetailsPage;
import lcguy.base;
import org.testng.Assert;
import org.testng.annotations.*;

public class P2_Navigation extends base {
    HomePage homepage;

    String properiesFilter = "Apartment";
    int propertyIndex = 2;



    @BeforeClass
    public void before() {
        initialDriver();
        allure_Log("Loading homepage");
        homepage = new HomePage();
    }

    @AfterClass
    public void after() {
        allure_Log("Closing driver");
        quitDriver();
    }

    @Test
    public void P1_checkHeader() {
        allure_LogAttachment("Checking if page loads successfully", "P2", "page_load");
        Assert.assertTrue(homepage.isHeaderDisplayed(), "TEST FAIL: Page header not displayed");
    }

    @Test
    public void P2_contactUs(){
        allure_Log("Navigate to contact us page");
        navigateContactUs();
        allure_LogAttachment("Contact Us Page", "P2", "contact_us");
        Assert.assertTrue(geBreadcrumbText().contains("contact"), "TEST FAIL: Not navigated to correct page");
    }

    @Test
    public void P3_PropertyDetails(){
        allure_Log("Navigate to property details page");
        navigatePropertyDetails();
        allure_LogAttachment("Property Details Page", "P2", "property_details");
        Assert.assertTrue(geBreadcrumbText().contains("property"), "TEST FAIL: Not navigated to correct page");
    }

    @Test
    public void P4_Properties(){
        allure_Log("Navigate to properties page");
        PropertiesPage propertiesPage = navigateProperties();
        allure_LogAttachment("Properties Page", "P2", "properties");
        boolean isCorrectPageAppears = geBreadcrumbText().contains("properties");
        allure_Log("Selecting filter " + properiesFilter);
        propertiesPage.filterSelect(properiesFilter);
        allure_LogAttachment("Filter Applied", "P2", "properties_filter");
        allure_Log("Selecting a property by index " + propertyIndex);
        String expectedPropertyName = propertiesPage.getName(propertyIndex);
        PropertyDetailsPage propertyDetailsPage = propertiesPage.select(propertyIndex);
        boolean isCorrectPropertySelected = propertyDetailsPage.getName().equals(expectedPropertyName);
        allure_LogAttachment("Selected Property", "P2", "properties_link");
        Assert.assertTrue(isCorrectPageAppears && isCorrectPropertySelected, "TEST FAIL: Not navigated to properties page \nOr selected property is mismatch name \nexpected: " + expectedPropertyName + "\n actual: " + propertyDetailsPage.getName());
    }

}
