package com.epam.lab.specific_elements;

import com.epam.lab.specific_elements.interfaces.IElement;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebElement;

public class Label extends IElement.Element {

    public Label(WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(CharSequence... charSequences) {
        throw new UnsupportedCommandException("Information can't be typed into label.");
    }

    public void clear() {
        throw new UnsupportedCommandException("Text can't be erased.");
    }
}
