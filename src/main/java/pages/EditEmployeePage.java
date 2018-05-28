package pages;

import common.Browser;
import common.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditEmployeePage extends EmployeeDetailPage {

    private static final Logger LOG;
    private static final String XPATH_UPDATEBUTTON;
    private static final String XPATH_DELETEBUTTON;
    private static final String XPATH_BACKBUTTON;

    public static EditEmployeePage getInstance(Settings settings){
        return new EditEmployeePage(settings);
    }
    public EditEmployeePage(Settings settings) {
        super(settings);
    }

    public boolean VerifyUpdateButtonPresent() {
        if (EditEmployeePage.LOG.isDebugEnabled()){
            EditEmployeePage.LOG.debug("Verifying Update button is shown after initiating editing");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_UPDATEBUTTON))).isDisplayed();
        }catch (Exception e){
            EditEmployeePage.LOG.error("Update button not visible");
        }
        return false;
    }

    public boolean VerifyDeleteButtonPresent() {
        if (EditEmployeePage.LOG.isDebugEnabled()){
            EditEmployeePage.LOG.debug("Verifying Delete button is shown after initiating editing");
        }
        try {
            return new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_DELETEBUTTON))).isDisplayed();
        }catch (Exception e){
            EditEmployeePage.LOG.error("Delete button not visible");
        }
        return false;
    }

    public void ClickUpdateButton() {
        if (EditEmployeePage.LOG.isDebugEnabled()){
            EditEmployeePage.LOG.debug("Clicking update button");
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_UPDATEBUTTON)));
        }catch (Exception e){
            EditEmployeePage.LOG.error("Can't click update button. Update button not visible");
            return;
        }

        Browser.getInstance().findElement(By.xpath(XPATH_UPDATEBUTTON)).click();
        pauseExecution();
    }

    private boolean delete(){
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_DELETEBUTTON)));
        }catch (Exception e){
            EditEmployeePage.LOG.error("Can't click delete button. Delete button not visible");
            return false;
        }

        Browser.getInstance().findElement(By.xpath(XPATH_DELETEBUTTON)).click();
        pauseExecution();
        return true;
    }

    public void DeleteAndAccept() {
        if (EditEmployeePage.LOG.isDebugEnabled()){
            EditEmployeePage.LOG.debug("Deleting and accepting to delete a record");
        }
        if (delete())
            Browser.getInstance().switchTo().alert().accept();

        pauseExecution();
    }

    public void DeleteAndReject() {
        if (EditEmployeePage.LOG.isDebugEnabled()){
            EditEmployeePage.LOG.debug("Deleting and rejecting to delete a record");
        }
        if (delete())
            Browser.getInstance().switchTo().alert().dismiss();

        pauseExecution();

    }

    public void ClickBackButton() {
        if (EditEmployeePage.LOG.isDebugEnabled()){
            EditEmployeePage.LOG.debug("Clicking back button while editing");
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_BACKBUTTON)));
        }catch (Exception e){
            EditEmployeePage.LOG.error("Can't click back button. Back button not visible");
        }

        Browser.getInstance().findElement(By.xpath(XPATH_BACKBUTTON)).click();
    }

    static {
        LOG = LogManager.getLogger(EditEmployeePage.class);
        XPATH_UPDATEBUTTON = Settings.getXpath("xpathUpdateButton");
        XPATH_DELETEBUTTON = Settings.getXpath("xpathDeleteButton");
        XPATH_BACKBUTTON = Settings.getXpath("xpathBackButton");
    }
}
