package com.epam.lab.business_objects;

import com.epam.lab.driver.DriverManager;
import com.epam.lab.page_objects.AuthorizationPage;
import com.epam.lab.page_objects.InboxPage;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static com.epam.lab.constants.Constants.START_URL;

public class MailBusinessObject{

    private Logger LOG = Logger.getLogger(MailBusinessObject.class);
    private AuthorizationPage authorizationPage;
    private InboxPage inboxPage;

    public MailBusinessObject() {
        WebDriver driver = DriverManager.getDriver();
        authorizationPage = new AuthorizationPage(driver);
        inboxPage = new InboxPage(driver);
        driver.get(START_URL);
    }

    public void logIn(User user) {
        LOG.info("=> Authorization...");
        authorizationPage.enterEmailAndClickNext(user.getEmail());
        authorizationPage.enterPasswordAndClickNext(user.getPassword());
    }

    public void selectMessages(int quantity) {
        LOG.info("=> Selecting messages to delete...");
        inboxPage.selectSeveralMessages(quantity);
    }

    public void deleteSelectedMessages() {
        LOG.info("=> Deleting messages...");
        inboxPage.deleteSelectedMessages();
    }

    public void undoDeleting(){
        LOG.info("=> Undo deleting...");
        inboxPage.undo();
    }

    public boolean isUndoCompleted() {
        return inboxPage.isUndoCompleted();
    }
}
