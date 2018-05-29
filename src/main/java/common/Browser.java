package common;

import common.Constants.Driver;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Browser {

    private static Logger LOG;
    private static WebDriver mDriver;
    private static String mScreenshotDirectory;

    public static WebDriver getInstance(){
        return mDriver;
    }

    static void initBrowser(Driver driver, Properties properties) {

        mScreenshotDirectory = properties.getProperty(Constants.SCREENSHOT_DIRECTORY);
        switch (driver){
            case CHROME: {
                final String driverHome = determineDriverHome(Constants.Driver.CHROME,properties);
                System.setProperty("webdriver.chrome.driver", driverHome);
                System.setProperty("webdriver.chrome.args", "--disable-logging");
                System.setProperty("webdriver.chrome.silentOutput", "true");
                final ChromeOptions co = new ChromeOptions();
                co.addArguments("--disable-extensions");
                co.addArguments("no-sandbox");
                if (StringUtils.isNotBlank(properties.getProperty(Driver.CHROME.getBrowserHome()))) {
                    co.setBinary(FilenameUtils.separatorsToSystem(properties.getProperty(Constants.Driver.CHROME.getBrowserHome())));
                    co.addArguments("--disable-extensions");
                    co.addArguments("no-sandbox");
                }
                mDriver = new ChromeDriver(co);
                mDriver.manage().timeouts().implicitlyWait(60L,TimeUnit.SECONDS);
                mDriver.manage().timeouts().pageLoadTimeout(60L,TimeUnit.SECONDS);
            }
                break;
            case FIREFOX:{
                LoggingPreferences pref = new LoggingPreferences();
                pref.enable(LogType.BROWSER, Level.OFF);
                pref.enable(LogType.CLIENT, Level.OFF);
                pref.enable(LogType.DRIVER, Level.OFF);
                pref.enable(LogType.PERFORMANCE, Level.OFF);
                pref.enable(LogType.PROFILER, Level.OFF);
                pref.enable(LogType.SERVER, Level.OFF);
                final String driverHome = determineDriverHome(Constants.Driver.FIREFOX,properties);
                System.setProperty("webdriver.gecko.driver", driverHome);
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
                final FirefoxProfile prof = new FirefoxProfile();
                prof.setPreference("dom.file.createInChild", true);
                prof.setPreference("browser.startup.homepage_override.mstone", "ignore");
                prof.setPreference("startup.homepage_welcome_url.additional", "about:blank");
                FirefoxOptions ffo = new FirefoxOptions();
                ffo.setProfile(prof);
                if (!StringUtils.isBlank(properties.getProperty(Driver.FIREFOX.getBrowserHome()))) {
                    final File pathToBinary = new File(FilenameUtils.separatorsToSystem(properties.getProperty(Constants.Driver.FIREFOX.getBrowserHome())));
                    final FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
                    ffo.setBinary(ffBinary);
                    mDriver = new FirefoxDriver(ffo);
                }
                else {
                    mDriver = new FirefoxDriver(ffo);
                }
            }
                break;
            case IE:
                break;
        }

        try {
            mDriver.manage().window().maximize();
            mDriver.manage().timeouts().setScriptTimeout(10L,TimeUnit.SECONDS);
        }
        catch (Exception ignored) {}
    }

    private static String determineDriverHome(final Constants.Driver driver, Properties props) {
        if (StringUtils.isNotBlank(props.getProperty(driver.getDriverHome()))) {
            return FilenameUtils.separatorsToSystem(props.getProperty(driver.getDriverHome()));
        }
        return FilenameUtils.separatorsToSystem("./resources/drivers/" + driver.getDriver());
    }

    public static void CaptureScreenshot(String name) {
        TakesScreenshot takesScreenshot = (TakesScreenshot)mDriver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File(mScreenshotDirectory.concat(name).concat(".png")));
        } catch (IOException e) {
            Browser.LOG.error(e.getMessage());
        }
    }

    static {
        LOG = LogManager.getLogger(Browser.class);
    }

    public static void CleanUp() {
        mDriver.quit();
        mDriver=null;
    }
}
