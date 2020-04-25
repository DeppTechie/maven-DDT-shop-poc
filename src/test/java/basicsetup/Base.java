/* Base class: Generic class that can be used to invoke the browser with given url, test level setup and test level tear down.
 * This class can be re used to pass driver instance to any other class to run the tests.
 * Here driver instance is created based on the input provided from properties file "testdata.properties".
 * Test level set up and tear down methods are defined.
 * 1)@BeforeTest : Test level set up method that reads input data like properties file path,
 *   driver type, driver path, browser type and url from properties file "testdata.properties" to invoke the browser with given url.
 * 2)@AfterTest : Test level tear down method is defined to tear down the test once test is completed.
 * No hard coding except the properties file path.
 */

package basicsetup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Base {
    protected static WebDriver driver;
    private static String baseUrl;
    private static String browser;

    @BeforeSuite
    public void readProps() throws IOException {

        String path = "src/test/java/testdata.properties";
        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream(path);
        prop.load(fs);
        baseUrl = prop.getProperty("url");
        browser = prop.getProperty("browser");
    }

    @BeforeTest
    public void setUp() {

        switch (browser){
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome"   :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Reporter.log("The Window is now maximized for better view", true);
        driver.get(baseUrl);
    }


    @AfterTest
    public void tearDown() throws IOException {
        driver.quit();
    }


}
