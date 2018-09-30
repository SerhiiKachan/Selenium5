package com.epam.lab.driver;

import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private DriverManager(){}

    private static final ThreadLocal<WebDriver> drivers = ThreadLocal.withInitial(() -> {
        Properties driverProperties = new MyParser().parsePropertiesFile(Constants.DRIVER_PROPERTIES_PATH);
        System.setProperty("webdriver.chrome.driver", driverProperties.getProperty("browser_driver"));
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(driverProperties.getProperty("implicit_wait")), TimeUnit.SECONDS);
        return driver;
    });

    public static WebDriver getDriver(){
        return drivers.get();
    }
}
