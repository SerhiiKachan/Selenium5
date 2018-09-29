import com.epam.lab.driver.DriverCreator;
import com.epam.lab.business_objects.MailBusinessObject;
import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.stream.IntStream;

import static com.epam.lab.constants.Constants.LOG4J_PROPERTIES_PATH;
import static com.epam.lab.constants.Constants.START_URL;
import static com.epam.lab.constants.Constants.USERNAME;

public class GMailTest {

    private MailBusinessObject mailBusinessObject;
    private final Logger LOG = Logger.getLogger(GMailTest.class);

    static {
        PropertyConfigurator.configure(LOG4J_PROPERTIES_PATH);
    }

    @DataProvider(parallel = true)
    public static Object[][] getUsers() {
        List<User> users = new MyParser().parseXML(Constants.USER_XML_PATH);
        return IntStream
                .range(0, 5)
                .mapToObj(i -> new Object[]{users.get(i)})
                .toArray(Object[][]::new);
    }

    @BeforeMethod
    public void init() {
        mailBusinessObject = new MailBusinessObject();
    }

    @Test(dataProvider = "getUsers")
    public void testUndoWithMessagesDeletion(User user) {
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
