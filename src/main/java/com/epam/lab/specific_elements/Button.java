package com.epam.lab.specific_elements;

import com.epam.lab.specific_elements.interfaces.IElement;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebElement;

public class Button extends IElement.Element {

    public Button(WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(CharSequence... charSequences) {
        throw new UnsupportedOperationException("Information can't be typed into button.");
    }

    public void clear() {
        throw new UnsupportedOperationException("Text can't be erased.");
    }

    public boolean isSelected() {
        throw new UnsupportedOperationException("Button can't be checked or unchecked.");
    }

}
