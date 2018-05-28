package pages;

import common.Browser;
import common.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LandingPage extends BasePage{
    private static final Logger LOG;
    private static final String XPATH_GREETINGS;
    private static final String XPATH_LOGOUTBUTTON;
    private static final String XPATH_CREATE;
    private static final String XPATH_EDIT;
    private static final String XPATH_DELETE;
    private static final String XPATH_EMPLOYEELIST;
    private static final String XPATH_ALLEMPLOYEE;
    private static final String XPATH_FIRSTEMPLOYEE;
    private final Settings mSettings;

    public LandingPage(Settings settings) {
        this.mSettings = settings;
    }

    public static LandingPage getInstance(Settings settings){
        return new LandingPage(settings);
    }

    public boolean IsGreetingsVisible() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying greetings is shown after logging in");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_GREETINGS))).isDisplayed();
        }catch (Exception e){
            LandingPage.LOG.error("Greetings not visible");
        }
        return false;
    }

    public boolean IsCreateButtonVisibleAndEnabled() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying Create button is shown after logging in");
        }
        try {
            return ((new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_CREATE))).isDisplayed())&&(new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_CREATE))).isEnabled()));
        }catch (Exception e){
            LandingPage.LOG.error("Create button not visible");
        }
        return false;
    }

    public boolean IsEditButtonVisible() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying Edit button is shown after logging in");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EDIT))).isDisplayed();
        }catch (Exception e){
            LandingPage.LOG.error("Edit button not visible");
        }
        return false;
    }

    public boolean IsDeleteButtonVisible() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying Delete button is shown after logging in");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_DELETE))).isDisplayed();
        }catch (Exception e){
            LandingPage.LOG.error("Delete button not visible");
        }
        return false;
    }

    public boolean IsEmployeeListVisible() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying Employee list is shown after logging in");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EMPLOYEELIST))).isDisplayed();
        }catch (Exception e){
            LandingPage.LOG.error("Employee list not visible");
        }
        return false;
    }

    public boolean IsLogoutButtonVisible() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying Logout button is shown after logging in");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_LOGOUTBUTTON))).isDisplayed();
        }catch (Exception e){
            LandingPage.LOG.error("Logout button not visible");
        }
        return false;
    }

    public void StartCreatingEmployee() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying Create button is shown after logging in");
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_CREATE)));
        }catch (Exception e){
            LandingPage.LOG.error("Create button not visible");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_CREATE)).click();
    }

    public void StartEditingEmployee() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying Editing of employee is possible");
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EDIT)));
        }catch (Exception e){
            LandingPage.LOG.error("Edit button not visible");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_EDIT)).click();
    }

    public boolean VerifyEmployeeNotPresent(String name) {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying Employee is not present:::name:::"+name);
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EMPLOYEELIST)));
        }catch (Exception e){
            LandingPage.LOG.error("Employee list not present");
            return false;
        }
        pauseExecution();
        List<WebElement> list = Browser.getInstance().findElements(By.xpath(XPATH_ALLEMPLOYEE));
        for (WebElement element : list){
            if (element.getText().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean VerifyEmployeePresent(String fname, String lname, String startdate, String email) {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying Employee is created with details as:::firstname:::"+fname+":::lastname:::"+lname+":::startdate:::"+startdate+":::email:::"+email);
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EMPLOYEELIST)));
        }catch (Exception e){
            LandingPage.LOG.error("Employee list not present");
            return false;
        }
        pauseExecution();
        String name = fname.concat(" ").concat(lname);
        List<WebElement> list = Browser.getInstance().findElements(By.xpath(XPATH_ALLEMPLOYEE));
        LandingPage.LOG.debug(name);
        for (WebElement element : list){
            if (element.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean OnlyOneEmployeeExistsWithName(String name) {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Verifying only one employee exists with name:::"+name);
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EMPLOYEELIST)));
        }catch (Exception e){
            LandingPage.LOG.error("Employee list not present");
            return false;
        }
        List<WebElement> list = Browser.getInstance().findElements(By.xpath(XPATH_ALLEMPLOYEE));
        int count = 0;
        for (WebElement element : list){
            if (element.getText().equals(name))
                count++;
            if (count >1) {
                deleteRecordsWithName(name);
                return false;
            }
        }
        if (count == 0){
            LandingPage.LOG.error("Employee not present");
            return false;
        }
        return true;
    }

    private void deleteRecordsWithName(String name) {
        List<WebElement> list = Browser.getInstance().findElements(By.xpath(XPATH_ALLEMPLOYEE));
        for (WebElement element : list){
            if (element.getText().equals(name)){
                element.click();
                Browser.getInstance().findElement(By.xpath(XPATH_DELETE)).click();
                Browser.getInstance().switchTo().alert().accept();
                pauseExecution();
                Browser.getInstance().switchTo().defaultContent();
            }
        }
    }

    public void ClickOnEmployeeInListWithName(String name) {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Selecting employee with name:::"+name);
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EMPLOYEELIST)));
        }catch (Exception e){
            LandingPage.LOG.error("Employee list not present");
            return;
        }
        List<WebElement> list = Browser.getInstance().findElements(By.xpath(XPATH_ALLEMPLOYEE));
        for (WebElement element : list){
            if (element.getText().equals(name)){
                element.click();
            }
        }
    }

    public void DeleteRecordsWithName(String name) throws InterruptedException {
        deleteRecordsWithName(name);
    }

    public void ClickDeleteButton() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Clicking delete button");
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_DELETE)));
        }catch (Exception e){
            LandingPage.LOG.error("Delete button not visible");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_DELETE)).click();
        pauseExecution();
    }

    public void CancelDeleteConfirmation() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Cancelling delete employee option");
        }
        Browser.getInstance().switchTo().alert().dismiss();
        pauseExecution();
    }

    public void OKDeleteConfirmation() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Cancelling delete employee option");
        }
        Browser.getInstance().switchTo().alert().accept();
        pauseExecution();
    }

    public void LogOut() {
        if (LandingPage.LOG.isDebugEnabled()){
            LandingPage.LOG.debug("Logging out");
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_LOGOUTBUTTON)));
        }catch (Exception e){
            LandingPage.LOG.error("Logout button not visible");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_LOGOUTBUTTON)).click();
        pauseExecution();
    }

    static {
        LOG = LogManager.getLogger(LandingPage.class);
        XPATH_GREETINGS = Settings.getXpath("xpathGreetings");
        XPATH_LOGOUTBUTTON = Settings.getXpath("xpathLogoutButton");
        XPATH_CREATE = Settings.getXpath("xpathCreate");
        XPATH_EDIT = Settings.getXpath("xpathEdit");
        XPATH_DELETE = Settings.getXpath("xpathDelete");
        XPATH_EMPLOYEELIST = Settings.getXpath("xpathEmployeeList");
        XPATH_ALLEMPLOYEE= Settings.getXpath("xpathAllEmployee");
        XPATH_FIRSTEMPLOYEE = Settings.getXpath("xpathFirstEmployee");
    }

}
