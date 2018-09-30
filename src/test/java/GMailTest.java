import com.epam.lab.driver.DriverManager;
import com.epam.lab.business_objects.MailBusinessObject;
import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Properties;

import static com.epam.lab.constants.Constants.LOG4J_PROPERTIES_PATH;
import static com.epam.lab.constants.Constants.START_URL;
import static com.epam.lab.constants.Constants.USERNAME;

public class GMailTest {

    private final Logger LOG = Logger.getLogger(GMailTest.class);

    static {
        PropertyConfigurator.configure(LOG4J_PROPERTIES_PATH);
    }

    @DataProvider(parallel = true)
    public static Object[] getUsers() {
        List<User> users = new MyParser().parseXML(Constants.USER_XML_PATH);
        return users.toArray();
    }

    @Test(dataProvider = "getUsers", threadPoolSize = 3)
    public void testUndoWithMessagesDeletion(User user) {
        LOG.info("TEST STARTED");
        LOG.info(USERNAME + user.getEmail());
        WebDriver driver = DriverManager.getDriver();
        driver.get(START_URL);
        MailBusinessObject mailBusinessObject = new MailBusinessObject(driver);
        mailBusinessObject.logIn(user);
        mailBusinessObject.selectMessages(3);
        mailBusinessObject.deleteSelectedMessages();
        Assert.assertTrue(mailBusinessObject.undoDeleting());
        LOG.info("TEST SUCCESSFULLY PASSED");
    }

    @AfterMethod
    public void exit() {
        DriverManager.getDriver().quit();
    }
}
