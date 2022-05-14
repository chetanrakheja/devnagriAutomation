package base;

import constants.DriverType;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    protected Properties ErrorProp;
    private InputStream inputStream2;

//    protected WebDriver getDriver(){
//        return this.driver;
//    }



    private void loadErrorProperties() {
        try{
            ErrorProp = new Properties();
            String errorProperties = "ErrorMsgs.properties";
            inputStream2 = getClass().getClassLoader().getResourceAsStream(errorProperties);
            ErrorProp.load(inputStream2);
        }catch (Exception e){
            System.out.println("Error in Loading Properties");
            e.printStackTrace();
        }
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser){
        loadErrorProperties();
        browser = System.getProperty("browser", browser);

        if(browser == null) browser = "CHROME";
        driver = DriverFactory.getWebDriverInstance(DriverType.CHROME);
//        setDriver(driver);
//        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
//
//
//        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
//                "DRIVER = " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws InterruptedException, IOException {
        Thread.sleep(300);
        DriverFactory.killWebDriverInstance();
    }

    protected void assertContains(String response,String containsStr){
        System.out.println("");
        System.out.println("Checking if Response contains => "+containsStr);
        System.out.println("Response =>" +response);
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertTrue(response.contains(containsStr));
    }

    protected void ThreadSleep10(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
