package lcguy.Pages;

import lcguy.base;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class ContactPage extends base {
    public By inp_form_name = By.id("name");
    By inp_form_email = By.id("email");
    By inp_form_subject = By.id("subject");
    By inp_form_message = By.id("message");
    By btn_form_submit = By.id("form-submit");

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
