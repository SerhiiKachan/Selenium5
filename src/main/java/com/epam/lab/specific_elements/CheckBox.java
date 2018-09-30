package com.epam.lab.specific_elements;

import com.epam.lab.specific_elements.interfaces.IElement;
import org.openqa.selenium.WebElement;

public class CheckBox extends IElement.Element {

    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public boolean isChecked() {
        return webElement.isSelected();
    }
}
