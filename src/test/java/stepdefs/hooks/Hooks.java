package stepdefs.hooks;

import common.Browser;
import common.InitSystem;
import common.Settings;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

    private Settings mSettings;
    private static Logger LOG;

    public Hooks(Settings settings){
        this.mSettings = settings;
    }

    @Before
    public void initializeTest(Scenario scenario){
        if (Hooks.LOG.isDebugEnabled()){
            Hooks.LOG.debug("Starting scenario::::".concat(scenario.getName()));
        }
        mSettings = new InitSystem().Init();
    }

    @After
    public void Cleanup(Scenario scenario){
        if (scenario.isFailed()){
            Browser.CaptureScreenshot(scenario.getName());
            Hooks.LOG.error("Scenario Failed:::"+scenario.getName());
        }else {
            Hooks.LOG.error("Scenario Passed:::"+scenario.getName());
        }
        Browser.CleanUp();
    }

    static {
        LOG = LogManager.getLogger(Hooks.class);
    }
}
