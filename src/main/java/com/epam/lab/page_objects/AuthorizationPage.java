package com.epam.lab.page_objects;

import com.epam.lab.constants.Constants;
import com.epam.lab.page_objects.decorator.CustomFieldDecorator;
import com.epam.lab.specific_elements.Button;
import com.epam.lab.specific_elements.TextInput;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage {

    private WebDriver driver;
    private final Logger LOG = Logger.getLogger(AuthorizationPage.class);

    @FindBy(xpath = "//input[@id='identifierId']")
    private TextInput email;

    @FindBy(id = "identifierNext")
    private Button emailNextButton;

    @FindBy(name = "password")
    private TextInput password;

    public AuthorizationPage(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void enterEmailAndClickNext(String mail) {
        LOG.info("Entering email...");
        email.fillInputWith(mail);
        LOG.info(Constants.COMPLETED);
        LOG.info("Submitting email...");
        emailNextButton.click();
        LOG.info(Constants.COMPLETED);
    }

    public void enterPasswordAndClickNext(String pass) {
        LOG.info("Entering password...");
        password.fillInputWith(pass);
        LOG.info(Constants.COMPLETED);
        LOG.info("Submitting password...");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('passwordNext').click();");
        LOG.info(Constants.COMPLETED);
    }
}
