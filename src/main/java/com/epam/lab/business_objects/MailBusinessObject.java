package com.epam.lab.business_objects;

import com.epam.lab.page_objects.AuthorizationPage;
import com.epam.lab.page_objects.InboxPage;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MailBusinessObject {

    private Logger LOG = Logger.getLogger(MailBusinessObject.class);
    private AuthorizationPage authorizationPage;
    private InboxPage inboxPage;

    public MailBusinessObject(WebDriver driver) {
        authorizationPage = new AuthorizationPage(driver);
        inboxPage = new InboxPage(driver);
    }

    public void logIn(User user) {
        LOG.info("Authorization...");
        authorizationPage.enterEmailAndClickNext(user.getEmail());
        authorizationPage.enterPasswordAndClickNext(user.getPassword());
    }

    public void selectMessages(int quantity) {
        inboxPage.selectSeveralMessages(quantity);
    }

    public void deleteSelectedMessages() {
        inboxPage.deleteSelectedMessages();
    }

    public boolean undoDeleting() {
        inboxPage.undo();
        return inboxPage.isUndoCompleted();
    }
}
