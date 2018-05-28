// 
// Decompiled by Procyon v0.5.30
// 

package common;

import pages.LoginPage;
import sun.rmi.runtime.Log;

public class Constants
{
    public static final String SCREENSHOT_DIRECTORY = "screenshot.directory";

    public enum Driver
    {
        CHROME("chrome.browser.home", "chrome.driver.home", "chromedriver.exe"), 
        FIREFOX("firefox.browser.home", "firefox.driver.home", "geckodriver.exe"), 
        IE("", "ie.driver.home", "IEDriverServer_32.exe");
        
        private String browserHome;
        private String driverHome;
        private String driver;
        
        private Driver(final String browserHome, final String driverHome, final String driver) {
            this.browserHome = browserHome;
            this.driverHome = driverHome;
            this.driver = driver;
        }
        
        public String getBrowserHome() {
            return this.browserHome;
        }
        
        public String getDriverHome() {
            return this.driverHome;
        }
        
        public String getDriver() {
            return this.driver;
        }
    }

    public enum LoginWarning
    {
        WRONGCREDENTIALS("Invalid username or password!"),
        EMPTYVALUE("Please fill out this field.");

        private String mWarningMsg;

        LoginWarning(String msg){
            this.mWarningMsg = msg;
        }

        public String getMessage(){
            return mWarningMsg;
        }
    }

    public enum UserOptions
    {
        CREATE,EDIT,DELETE;
    }
}
