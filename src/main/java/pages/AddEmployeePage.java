package pages;

import common.Browser;
import common.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AddEmployeePage extends EmployeeDetailPage {

    private static final Logger LOG;
    private static final String XPATH_ADDBUTTON;
    private Settings mSettings;

    public static AddEmployeePage getInstance(Settings settings){
        return new AddEmployeePage(settings);
    }

    public AddEmployeePage(Settings settings){
        super(settings);
        this.mSettings = settings;
    }


    public void ClickAddButton() {
        if (AddEmployeePage.LOG.isDebugEnabled()){
            AddEmployeePage.LOG.debug("Clicking Add button to submit detail");
        }
        try {
            new WebDriverWait(Browser.getInstance(),30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_ADDBUTTON)));
            Browser.getInstance().findElement(By.xpath(XPATH_ADDBUTTON)).click();
            pauseExecution();
        }catch (Exception e){
            AddEmployeePage.LOG.error("Can not submit details. Add button is not visible");
            return;
        }
    }

    static {
        LOG = LogManager.getLogger(AddEmployeePage.class);
        XPATH_ADDBUTTON = Settings.getXpath("xpathAddButton");
    }
}
