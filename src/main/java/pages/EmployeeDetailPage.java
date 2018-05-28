package pages;

import common.Browser;
import common.Settings;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDetailPage extends BasePage {
    private static final Logger LOG;
    private static final String XPATH_ADDFIRSTNAME;
    private static final String XPATH_ADDLASTNAME;
    private static final String XPATH_ADDSTARTDATE;
    private static final String XPATH_ADDEMAIL;
    private static final Pattern STARTDATEPATTERN;
    private Settings mSettings;

    public EmployeeDetailPage(Settings settings){
        this.mSettings = settings;
    }

    public boolean IsAddFirstNameVisible() {
        if (EmployeeDetailPage.LOG.isDebugEnabled()){
            EmployeeDetailPage.LOG.debug("Verifying user is shown field to add the first name");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_ADDFIRSTNAME))).isDisplayed();
        }catch (Exception e){
            EmployeeDetailPage.LOG.error("First name field is not visible");
        }
        return false;
    }

    public boolean IsAddLastNameVisible() {
        if (EmployeeDetailPage.LOG.isDebugEnabled()){
            EmployeeDetailPage.LOG.debug("Verifying user is shown field to add the last name");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_ADDLASTNAME))).isDisplayed();
        }catch (Exception e){
            EmployeeDetailPage.LOG.error("Last name field is not visible");
        }
        return false;
    }

    public boolean IsAddStartDateVisible() {
        if (EmployeeDetailPage.LOG.isDebugEnabled()){
            EmployeeDetailPage.LOG.debug("Verifying user is shown field to add the start date");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_ADDSTARTDATE))).isDisplayed();
        }catch (Exception e){
            EmployeeDetailPage.LOG.error("Start date field is not visible");
        }
        return false;
    }

    public boolean IsAddEmailVisible() {
        if (EmployeeDetailPage.LOG.isDebugEnabled()){
            EmployeeDetailPage.LOG.debug("Verifying user is shown field to add the email");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_ADDEMAIL))).isDisplayed();
        }catch (Exception e){
            EmployeeDetailPage.LOG.error("Email field is not visible");
        }
        return false;
    }

    public void AddFirstName(String fname) {
        if (EmployeeDetailPage.LOG.isDebugEnabled()){
            EmployeeDetailPage.LOG.debug("Adding the first name:::"+fname);
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_ADDFIRSTNAME)));
        }catch (Exception e){
            EmployeeDetailPage.LOG.error("Can not add first name. First name field is not visible");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_ADDFIRSTNAME)).clear();
        Browser.getInstance().findElement(By.xpath(XPATH_ADDFIRSTNAME)).sendKeys(fname);
    }

    public void AddLastName(String lname) {
        if (EmployeeDetailPage.LOG.isDebugEnabled()){
            EmployeeDetailPage.LOG.debug("Adding the last name:::"+lname);
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_ADDLASTNAME)));
        }catch (Exception e){
            EmployeeDetailPage.LOG.error("Can not add last name. Last name field is not visible");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_ADDLASTNAME)).clear();
        Browser.getInstance().findElement(By.xpath(XPATH_ADDLASTNAME)).sendKeys(lname);
    }

    public void AddStartDate(String sdate) {
        if (EmployeeDetailPage.LOG.isDebugEnabled()){
            EmployeeDetailPage.LOG.debug("Adding the start date:::"+sdate);
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_ADDSTARTDATE)));
            if (!verifyStartDateFormat(sdate)){
                EmployeeDetailPage.LOG.error("Can not add start date. Start date not in correct format");
                return;
            }
        }catch (Exception e){
            EmployeeDetailPage.LOG.error("Can not add start date. Start date field is not visible");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_ADDSTARTDATE)).clear();
        Browser.getInstance().findElement(By.xpath(XPATH_ADDSTARTDATE)).sendKeys(sdate);
    }

    private boolean verifyStartDateFormat(String sdate) {
        Matcher matcher = STARTDATEPATTERN.matcher(sdate);
        return matcher.matches();
    }

    public void AddEmail(String email) {
        if (EmployeeDetailPage.LOG.isDebugEnabled()){
            EmployeeDetailPage.LOG.debug("Adding the email:::"+email);
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_ADDEMAIL)));
            if (!verifyEmailAddress(email)){
                EmployeeDetailPage.LOG.error("Can not add email. Email id not in correct format");
                return;
            }
        }catch (Exception e){
            EmployeeDetailPage.LOG.error("Can not add email. Email field is not visible");
            return;
        }
        Browser.getInstance().findElement(By.xpath(XPATH_ADDEMAIL)).clear();
        Browser.getInstance().findElement(By.xpath(XPATH_ADDEMAIL)).sendKeys(email);
    }

    private boolean verifyEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    static {
        LOG = LogManager.getLogger(AddEmployeePage.class);
        XPATH_ADDFIRSTNAME = Settings.getXpath("xpathAddFirstName");
        XPATH_ADDLASTNAME = Settings.getXpath("xpathAddLastName");
        XPATH_ADDSTARTDATE = Settings.getXpath("xpathAddStartDate");
        XPATH_ADDEMAIL = Settings.getXpath("xpathAddEmail");
        STARTDATEPATTERN = Pattern.compile(Settings.getDateFormatPattern("dateFormatStartDate"));
    }
}
