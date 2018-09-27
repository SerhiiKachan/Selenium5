package com.epam.lab.specific_elements.interfaces;

import org.openqa.selenium.*;

public interface IElement {

    class Element implements IElement {

        protected WebElement webElement;

        public Element(WebElement webElement) {
            this.webElement = webElement;
        }

        public void click() {
            webElement.click();
        }

        public String getAttribute(String name) {
            return webElement.getAttribute(name);
        }
    }
}
