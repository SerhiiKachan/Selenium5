package com.epam.lab.page_objects;

import com.epam.lab.constants.Constants;
import com.epam.lab.driver.DriverManager;
import com.epam.lab.page_objects.decorator.CustomFieldDecorator;
import com.epam.lab.parser.MyParser;
import com.epam.lab.specific_elements.Button;
import com.epam.lab.specific_elements.CheckBox;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.epam.lab.constants.Constants.ID;
import static com.epam.lab.constants.Constants.COMPLETED;

public class InboxPage {

    private WebDriver driver;
    private List<String> identifiers;
    private final Logger LOG = Logger.getLogger(InboxPage.class);

    @FindBy(css = "table.F.cf.zt tbody div[role='checkbox']")
    private List<CheckBox> messages;

    @FindBy(css = "div.T-I.J-J5-Ji.nX.T-I-ax7.T-I-Js-Gs.mA")
    private Button deleteButton;

    @FindBy(id = "link_undo")
    private Button undoButton;


    public InboxPage(WebDriver driver) {
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
        identifiers = new ArrayList<>();
        this.driver = driver;
    }

    private void selectMessage(CheckBox message) {
        message.waitForToBeAttachedToTheDOM();
        try{
            if (!message.isChecked())
                message.click();
            identifiers.add(message.getAttribute(ID));
        }catch (StaleElementReferenceException e) {
            if (!message.isChecked())
                message.click();
            identifiers.add(message.getAttribute(ID));
        }
        LOG.info("Message selected");
    }

    public void deleteSelectedMessages() {
        LOG.info("Deleting messages...");
        deleteButton.waitUntilElementToBeClickableAndClick();
    }

    public void undo() {
        LOG.info("Undo deleting...");
        undoButton.waitUntilElementToBeClickableAndClick();
    }

    public void selectSeveralMessages(int quantity) {
        LOG.info("Selecting messages to delete...");
        LOG.info(messages.size());
        if (messages.size() > quantity) {
            int i = 0;
            while (i < quantity) {
                selectMessage(messages.get(i));
                i++;
            }
        }
    }

    public boolean isUndoCompleted() {
        LOG.info("Checking undo operation...");
        try {
            int i = 0;
            while (i < identifiers.size()) {
                messages.get(i).waitForToBeAttachedToTheDOM();
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
