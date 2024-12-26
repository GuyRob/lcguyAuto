import lcguy.Pages.ContactPage;
import lcguy.Pages.HomePage;
import lcguy.Pages.SubmitPage;
import lcguy.base;
import org.testng.Assert;
import org.testng.annotations.*;

public class P3_Form extends base {
    HomePage homepage;

    String fullName = "Guy R";
    String email = "guy@ab.com";
    String subject = "More details about villa";
    String message = "Hey,\nabout the villa in Portland, can I bring dog?";

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
    public void P1_homePageForm() {
        allure_Log("Scrolling to form section");
        scroll_Element(homepage.txt_contactUs);
        allure_LogAttachment("Form Section", "P3", "homepage_form_section");
        allure_Log("Filling form data");
        homepage.form_sendName(fullName);
        homepage.form_sendEmail(email);
        homepage.form_sendSubject(subject);
        homepage.form_sendMessage(message);
        allure_LogAttachment("Form Details", "P3", "homepage_form_details");
        allure_Log("Submitting form");
        SubmitPage submitPage = homepage.form_submit();
        allure_LogAttachment("Submit Page", "P3", "homepage_form_submit");
        Assert.assertTrue(submitPage.isPageDisplayed(), "TEST FAIL: Submit page is not displayed");
    }

    @Test
    public void P2_contactPageForm() {
        allure_Log("Navigating to contact us page");
        ContactPage contactPage = navigateContactUs();
        scroll_Element(contactPage.inp_form_name);
        allure_LogAttachment("Form Section", "P3", "contactUs_nav");
        allure_Log("Filling form data with wrong email");
        contactPage.form_sendName(fullName);
        contactPage.form_sendEmail(fullName); // Wrong email to see popup
        contactPage.form_sendSubject(subject);
        contactPage.form_sendMessage(message);
        contactPage.form_submit();
        allure_LogAttachment("Showing error on email", "P3", "contactUs_error");
        allure_Log("Fixing email and submit");
        contactPage.form_sendEmail(email);
        SubmitPage submitPage =contactPage.form_submit();
        allure_LogAttachment("Submit Page", "P3", "contactUs_form_submit");
        Assert.assertTrue(submitPage.isPageDisplayed(), "TEST FAIL: Submit page is not displayed");
    }

}
