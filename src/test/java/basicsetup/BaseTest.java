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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testclass.CustomListener;


public class BaseTest {
    public WebDriver driver;

    @BeforeTest
    public WebDriver setUp() throws IOException {

        //Properties file path
        String path = "src/test/java/testdata.properties";

        //Creating object of Properties class
        Properties prop = new Properties();

        //Creating object of FileInputStream class and providing the properties file path to the object
        FileInputStream fs = new FileInputStream(path);

        prop.load(fs);

        //Invoking the browser based on the input provided from the properties file
        if (prop.getProperty("browser").equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            Reporter.log("Running test on Firefox browser", true);
        } else if (prop.getProperty("browser").equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            Reporter.log("Running test on chrome browser", true);
        } else {
            driver = new InternetExplorerDriver();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Reporter.log("The Window is now maximized for better view", true);
        driver.get(prop.getProperty("url"));
        return driver;
    }

    public void failed() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("src/screenshots/testFailure.jpg"));
        Reporter.log("The Test has failed,Please see the screenshot in the Target folder", true);

    }


    @AfterTest
    public void tearDown() throws IOException {
        driver.quit();
    }


}
