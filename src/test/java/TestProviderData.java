import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import com.epam.lab.parser.XML_models.User;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.stream.IntStream;

public class TestProviderData {

    @DataProvider(parallel = true)
    public static Object[][] getInformation() {
        List<User> usersData = new MyParser().parseXML(Constants.USER_XML_PATH);
        return IntStream
                .range(0, 5)
                .mapToObj(i -> new Object[]{usersData.get(i)})
                .toArray(Object[][]::new);
    }
}
