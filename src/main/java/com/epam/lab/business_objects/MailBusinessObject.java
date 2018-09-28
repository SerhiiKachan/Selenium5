package com.epam.lab.business_objects;

import com.epam.lab.driver.DriverCreator;
import com.epam.lab.page_objects.AuthorizationPage;
import com.epam.lab.page_objects.InboxPage;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;

public class MailBusinessObject {

    private Logger LOG = Logger.getLogger(MailBusinessObject.class);
    private AuthorizationPage authorizationPage;
    private InboxPage inboxPage;

    public MailBusinessObject() {
        authorizationPage = new AuthorizationPage(DriverCreator.getDriver());
        inboxPage = new InboxPage(DriverCreator.getDriver());
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
