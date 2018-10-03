import com.epam.lab.business_objects.MailBusinessObject;
import com.epam.lab.driver.DriverManager;
import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.epam.lab.constants.Constants.LOG4J_PROPERTIES_PATH;

public class GMailTest {

    private final Logger LOG = Logger.getLogger(GMailTest.class);

    static {
        PropertyConfigurator.configure(LOG4J_PROPERTIES_PATH);
    }

    @Test(dataProviderClass = DataProviderForTest.class, dataProvider = "dp")
    public void testUndoWithMessagesDeletion(User user) {
        LOG.info("TEST STARTED");
        MailBusinessObject mailBusinessObject = new MailBusinessObject();
        mailBusinessObject.logIn(user);
        mailBusinessObject.selectMessages(3);
        mailBusinessObject.deleteSelectedMessages();
        mailBusinessObject.undoDeleting();
        Assert.assertTrue(mailBusinessObject.isUndoCompleted());
        LOG.info("TEST SUCCESSFULLY PASSED");
    }

    @AfterMethod(alwaysRun = true)
    public void exit() {
        DriverManager.exit();
    }
}
