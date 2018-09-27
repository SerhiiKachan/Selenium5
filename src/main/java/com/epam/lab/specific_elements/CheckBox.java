package com.epam.lab.specific_elements;

import com.epam.lab.specific_elements.interfaces.IElement;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebElement;

public class CheckBox extends IElement.Element {

    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public boolean isChecked() {
        return webElement.isSelected();
    }

    public void sendKeys(CharSequence... charSequences) {
        throw new UnsupportedCommandException("Information can't be typed into checkbox.");
    }

    public void clear() {
        throw new UnsupportedCommandException("Checkbox has no information to be cleared.");
    }
}
