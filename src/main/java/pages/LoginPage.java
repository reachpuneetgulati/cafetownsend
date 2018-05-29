package pages;

import common.Browser;
import common.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final Logger LOG;
    private static final String XPATH_USERNAME;
    private static final String XPATH_PASSWORD;
    private static final String XPATH_LOGINBUTTON;
    private static final String XPATH_INVALIDLOGIN;
    private Settings mSettings;


    public LoginPage(Settings settings) {
        this.mSettings = settings;
    }

    public static LoginPage getInstance(Settings settings){
        return new LoginPage(settings);
    }

    static{
        LOG = LogManager.getLogger(LoginPage.class);
        XPATH_USERNAME = Settings.getXpath("xpathUserName");
        XPATH_PASSWORD = Settings.getXpath("xpathPassword");
        XPATH_LOGINBUTTON = Settings.getXpath("xpathLoginButton");
        XPATH_INVALIDLOGIN = Settings.getXpath("xpathLoginErrorMessage");
    }

    public void GoTo() {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Navigating to login page"+mSettings.getmHomepage());
        }
        try {
//            Browser.getInstance().get("http://cafetownsend-angular-rails.herokuapp.com/login");
            Browser.getInstance().get(mSettings.getmHomepage());
        }catch (Exception e){
            LoginPage.LOG.error("Can't navigate to the login page:::"+mSettings.getmHomepage());
        }
    }
    public void GoTo(String site) {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Navigating to page:::"+site);
        }
        try {
            Browser.getInstance().get(site);
        }catch (Exception e){
            LoginPage.LOG.error("Can't navigate to the page:::"+site);
        }
    }

    public boolean IsUserNameVisible() {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Verifying username field is visible after reaching the login page");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_USERNAME))).isDisplayed();
        }catch (Exception e){
            LoginPage.LOG.error("Username element not visible");
        }
        return false;
    }

    public void EnterUserName(String value) {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Entering user name:::"+value);
        }
        try {
            new WebDriverWait(Browser.getInstance(),60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_USERNAME)));
        }catch (Exception e){
            LoginPage.LOG.error("Can't find element to enter username");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_USERNAME)).clear();
        Browser.getInstance().findElement(By.xpath(XPATH_USERNAME)).sendKeys(value);
    }

    public void EnterPassword(String value) {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Entering password:::"+value);
        }
        try {
            new WebDriverWait(Browser.getInstance(),60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_USERNAME)));
        }catch (Exception e){
            LoginPage.LOG.error("Can't find element to enter password");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_PASSWORD)).clear();
        Browser.getInstance().findElement(By.xpath(XPATH_PASSWORD)).sendKeys(value);
    }

    public boolean IsPasswordVisible() {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Verifying password field is visible after reaching the login page");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_PASSWORD))).isDisplayed();
        }catch (Exception e){
            LoginPage.LOG.error("Password element not visible");
        }
        return false;
    }

    public boolean IsLoginButtonVisible() {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Verifying Login button is visible after reaching the login page");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_LOGINBUTTON))).isDisplayed();
        }catch (Exception e){
            LoginPage.LOG.error("Login button not visible");
        }
        return false;
    }

    public void ClickLoginButton() {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Clicking Login button on login page");
        }
        try {
            new WebDriverWait(Browser.getInstance(),60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_LOGINBUTTON)));
        }catch (Exception e){
            LoginPage.LOG.error("Login button not visible");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_LOGINBUTTON)).click();
    }

    public boolean IsInValidLogin() {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Checking for invalid login message");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_INVALIDLOGIN))).isDisplayed();
        }catch (Exception e){
            LoginPage.LOG.error("Valid login");
            return false;
        }
    }

    public boolean IsCredentialNotProvided() {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Checking for empty credentials message");
        }
        return false;
    }

    public void LoginWithUsernameAndPassword(String username, String password) {
        if (LoginPage.LOG.isDebugEnabled()){
            LoginPage.LOG.debug("Logging in with username:::"+username+" and password:::"+password);
        }
        try {
            new WebDriverWait(Browser.getInstance(),60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_USERNAME)));
            new WebDriverWait(Browser.getInstance(),60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_PASSWORD)));
            new WebDriverWait(Browser.getInstance(),60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_LOGINBUTTON)));
        }catch (Exception e){
            LoginPage.LOG.error("Can't find element to enter password");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_USERNAME)).clear();
        Browser.getInstance().findElement(By.xpath(XPATH_USERNAME)).sendKeys(username);
        Browser.getInstance().findElement(By.xpath(XPATH_PASSWORD)).clear();
        Browser.getInstance().findElement(By.xpath(XPATH_PASSWORD)).sendKeys(password);
        Browser.getInstance().findElement(By.xpath(XPATH_LOGINBUTTON)).click();
    }

}
