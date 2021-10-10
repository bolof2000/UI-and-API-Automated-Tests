package base;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.EventReporter;
import utils.WindowManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTests {

    private EventFiringWebDriver driver;
    public HomePage homePage;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/Volumes/Projects/Software Testings/Automations-Tests/src/main/resources/drivers/chromedriver");
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        driver.register(new EventReporter());
    }

    @BeforeMethod
    public void goHome(){
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().fullscreen();
        homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @AfterMethod
    public void recordTestResults(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("/Volumes/Projects/Software Testings/Automations-Tests/src/main/resources/screenshots/fail/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        else if (ITestResult.SUCCESS == result.getStatus()){
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("/Volumes/Projects/Software Testings/Automations-Tests/src/main/resources/screenshots/pass/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("/Volumes/Projects/Software Testings/Automations-Tests/src/main/resources/screenshots/other_status/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public WindowManager getWindowManager(){
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return options;
    }
    private FirefoxOptions getFirefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("disable-ifobars");

        return options;
    }

}
