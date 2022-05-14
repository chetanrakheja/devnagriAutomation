package factory;

import constants.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver webDriver;
    private static WebDriverManager webDriverManager;



    /* W E B  M A N A G E R   D R I V E R   S E R V I C E S */

    public static WebDriver getWebDriverInstance(DriverType driverType) {

        switch (driverType) {
            case CHROME:
                webDriver = createChromeWebDriverInstance();
                break;
//            case FIREFOX:
//                webDriver = createFirefoxWebDriverInstance();
//                break;
//            case IE:
//                webDriver = createieWebDriverInstance();
//                break;
            case EDGE:
                webDriver = createEdgeWebDriverInstance();
                break;
//            case PHANTOMJS:
//                webDriver = createPhantomDriverInstance();
//                break;
//            case SAFARI:
//                webDriver = createSafariWebDriverInstance();
//                break;
//            case OPERA:
//                webDriver = createOperaWebDriverInstance();
//                break;
            default:
                webDriver = createChromeWebDriverInstance();
        }
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        return webDriver;
    }


    public static boolean isDriverInstanceExist() {
        if (webDriver != null)
            return true;
        else return false;
    }


    public static WebDriver createChromeWebDriverInstance() {
        webDriverManager.getInstance(DriverManagerType.CHROME).setup();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
//        chromePrefs.put("download.default_directory", PathConfig.getBrowserFileDownloadPath());
        chromePrefs.put(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.addArguments("--ignore-certificate-errors");
        options.setCapability(ChromeOptions.CAPABILITY, options);
        options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
//        options.setCapability("chrome.switches", Arrays.asList("--incognito"));
        // options.addArguments("--headless");


        return new ChromeDriver(options);

    }

//    public static WebDriver createFirefoxWebDriverInstance() {
//        webDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
//        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//        capabilities.setCapability("dom.disable_beforeunload", true);
//        capabilities.setCapability("browser.privatebrowsing.autostart", true);
//        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        FirefoxProfile profile = new FirefoxProfile();
//        FirefoxOptions options = new FirefoxOptions(capabilities);
//        return new FirefoxDriver(options);
//    }

    public static WebDriver createieWebDriverInstance() {
        webDriverManager.getInstance(DriverManagerType.IEXPLORER).setup();
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability("browserstack.ie.enablePopups", "false");
        // options.setCapability(CapabilityType.BROWSER_VERSION, "11");

        return new InternetExplorerDriver(options);
    }

    public static WebDriver createEdgeWebDriverInstance() {
        webDriverManager.getInstance(DriverManagerType.EDGE).setup();

        EdgeOptions egdeOptions = new EdgeOptions();
        egdeOptions.setCapability("profile.default_content_settings.popups", 0);
        egdeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        egdeOptions.setCapability("in_private", true);
        egdeOptions.setCapability(CapabilityType.PLATFORM_NAME, "ANY");
        egdeOptions.setCapability("edge_binary", "Applications");
        return new EdgeDriver(egdeOptions);
    }

    public static WebDriver createPhantomDriverInstance() {
        return new EdgeDriver();
    }

    public static WebDriver createSafariWebDriverInstance() {
        webDriverManager.getInstance(DriverManagerType.SAFARI).setup();
        SafariOptions options = new SafariOptions();
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return new SafariDriver(options);
    }

    public static WebDriver createOperaWebDriverInstance() {
        webDriverManager.getInstance(DriverManagerType.OPERA).setup();
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        operaOptions.addArguments("private");
        return new OperaDriver(operaOptions);
    }


    public static void killWebDriverInstance() {

        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }


}
