package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(format = {"pretty","html:reports"},features = {"src/test/features"}, glue = {"stepdefs"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
