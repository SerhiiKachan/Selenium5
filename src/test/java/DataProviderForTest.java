import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import org.testng.annotations.DataProvider;

public class DataProviderForTest {

    @DataProvider(name = "dp", parallel = true)
    public static Object[] getUsers() {
        return new MyParser().parseXML(Constants.USER_XML_PATH);
    }
}
