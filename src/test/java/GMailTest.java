import com.epam.lab.Driver.DriverCreator;
import com.epam.lab.business_objects.MailBusinessObject;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.epam.lab.constants.Constants.LOG4J_PROPERTIES_PATH;
import static com.epam.lab.constants.Constants.START_URL;
import static com.epam.lab.constants.Constants.USERNAME;

public class GMailTest {

    private User user;
    private MailBusinessObject mailBusinessObject;
    private final Logger LOG = Logger.getLogger(GMailTest.class);

    static {
        PropertyConfigurator.configure(LOG4J_PROPERTIES_PATH);
    }

    @Factory(dataProviderClass = TestProviderData.class, dataProvider = "getInformation")
    public GMailTest(User user) {
        this.user = user;
    }

    @BeforeMethod
    public void init() {
        mailBusinessObject = new MailBusinessObject();
    }

    @Test
    public void testUndoWithMessagesDeletion() {
        LOG.info(USERNAME + user.getEmail());
        LOG.info("TEST STARTED");
        DriverCreator.getDriver().get(START_URL);
        mailBusinessObject.logIn(user);
        mailBusinessObject.selectMessages(4);
        mailBusinessObject.deleteSelectedMessages();
        Assert.assertTrue(mailBusinessObject.undoDeleting());
        LOG.info("TEST SUCCESSFULLY PASSED");
    }

    @AfterMethod
    public void exit() {
        DriverCreator.destroy();
    }
}
