package com.epam.lab.specific_elements.interfaces;

import com.epam.lab.driver.DriverCreator;
import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public interface IElement {

    class Element implements IElement {

        protected WebElement webElement;
        WebDriverWait wait;

        public Element(WebElement webElement) {
            Properties driverProperties = new MyParser().parsePropertiesFile(Constants.DRIVER_PROPERTIES_PATH);
            this.webElement = webElement;
            this.wait = new WebDriverWait(DriverCreator.getDriver(), Long.parseLong(driverProperties.getProperty("explicit_wait")));
        }

        public void click() {
            webElement.click();
        }

        public void fillInputWith(CharSequence... charSequences){
            webElement.sendKeys(charSequences);
        }

        public String getAttribute(String name) {
            return webElement.getAttribute(name);
        }

        WebElement getWebElement() {
            return webElement;
        }

        public void waitUntilElementToBeClickableAndClick() {
            wait.until(ExpectedConditions.elementToBeClickable(this.getWebElement())).click();
        }

        public void waitForPresenceOfElement(){
            wait.until(ExpectedConditions.visibilityOf(this.webElement));
        }

        public void waitForToBeAttachedToTheDOM(){
            wait.withTimeout(2, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
        }
    }
}
