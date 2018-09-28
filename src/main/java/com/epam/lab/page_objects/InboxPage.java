package com.epam.lab.page_objects;

import com.epam.lab.page_objects.decorator.CustomFieldDecorator;
import com.epam.lab.specific_elements.Button;
import com.epam.lab.specific_elements.CheckBox;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.epam.lab.constants.Constants.ID;
import static com.epam.lab.constants.Constants.COMPLETED;

public class InboxPage {

    private List<String> identifiers;
    private final Logger LOG = Logger.getLogger(InboxPage.class);

    @FindBy(css = "table.F.cf.zt tbody div[role='checkbox']")
    private List<CheckBox> messages;

    @FindBy(css = "div.T-I.J-J5-Ji.nX.T-I-ax7.T-I-Js-Gs.mA")
    private Button deleteButton;

    @FindBy(id = "link_undo")
    private Button undoButton;

    public InboxPage(WebDriver webDriver) {
        PageFactory.initElements(new CustomFieldDecorator(webDriver), this);
        identifiers = new ArrayList<>();
    }

    private void selectMessage(CheckBox message, int index) {
        message.waitForToBeAttachedToTheDOM();
        message.waitForPresenceOfElement();
        if (!message.isChecked())
            message.click();
        message = messages.get(index);
        identifiers.add(message.getAttribute(ID));
        LOG.info("Message selected");
    }

    public void deleteSelectedMessages() {
        LOG.info("Deleting messages...");
        deleteButton.waitUntilElementToBeClickableAndClick();
        LOG.info(COMPLETED);
    }

    public void undo() {
        LOG.info("Undo deleting...");
        undoButton.waitUntilElementToBeClickableAndClick();
        LOG.info(COMPLETED);
    }

    public void selectSeveralMessages(int quantity) {
        LOG.info("Selecting messages to delete...");
        int i = 0;
        if (messages.size() > quantity) {
            while (i < quantity) {
                selectMessage(messages.get(i), i);
                i++;
            }
        } else {
            selectAllMessages();
        }
    }

    private void selectAllMessages() {
        int i = 0;
        while (i < messages.size()) {
            selectMessage(messages.get(i), i);
            i++;
        }
    }

    public boolean isUndoCompleted() {
        LOG.info("Checking undo operation...");
        try {
            int i = 0;
            while (i < identifiers.size()) {
                messages.get(i).waitForToBeAttachedToTheDOM();
                messages.get(i).waitForPresenceOfElement();
                if (!messages.get(i).getAttribute(ID).equals(identifiers.get(i)))
                    throw new NoSuchElementException("Can't find element with id=" + identifiers.get(i) + ". Element has been deleted.");
                i++;
            }
            LOG.info(COMPLETED);
            return true;
        } catch (NoSuchElementException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }
}
