package com.epam.lab.driver;

import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverCreator {

    private static Map<Long, WebDriver> drivers = new HashMap<>();

    private DriverCreator() {
    }

    private static WebDriver newInstance() {
        Properties driverProperties = new MyParser().parsePropertiesFile(Constants.DRIVER_PROPERTIES_PATH);
        System.setProperty("webdriver.chrome.driver", driverProperties.getProperty("browser_driver"));
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(driverProperties.getProperty("implicit_wait")), TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver getDriver() {
        return drivers.computeIfAbsent(Thread.currentThread().getId(), l -> DriverCreator.newInstance());
    }

    public static void remove() {
        drivers.remove(Thread.currentThread().getId()).quit();
    }
}
