import com.epam.lab.driver.DriverCreator;
import com.epam.lab.business_objects.MailBusinessObject;
import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.epam.lab.constants.Constants.LOG4J_PROPERTIES_PATH;
import static com.epam.lab.constants.Constants.START_URL;
import static com.epam.lab.constants.Constants.USERNAME;

public class GMailTest {

    private MailBusinessObject mailBusinessObject;
    private User user;
    private final Logger LOG = Logger.getLogger(GMailTest.class);

    static {
        PropertyConfigurator.configure(LOG4J_PROPERTIES_PATH);
    }

    @Factory(dataProviderClass = GMailTest.class, dataProvider = "getUsers")
    public GMailTest(User user) {
        this.user = user;
    }

    @DataProvider(parallel = true)
    public static Object[] getUsers() {
        return new MyParser().parseXML(Constants.USER_XML_PATH);
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
        mailBusinessObject.selectMessages(3);
        mailBusinessObject.deleteSelectedMessages();
        Assert.assertTrue(mailBusinessObject.undoDeleting());
        LOG.info("TEST SUCCESSFULLY PASSED");
    }

    @AfterMethod
    public void exit() {
        DriverCreator.remove();
    }
}
