package stepdefs.login;

import common.Settings;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import pages.LoginPage;
import stepdefs.base.BaseStep;

public class CommonSteps extends BaseStep {
    private Settings mSettings;

    public CommonSteps(BaseStep step){
        this.mSettings = step.getmSettings();
    }

    @Given("^I am logged in to the website with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iAmLoggedInToTheWebsiteWithUsernameAndPassword(String username, String password) {
        LoginPage.getInstance(mSettings).GoTo();
        LoginPage.getInstance(mSettings).LoginWithUsernameAndPassword(username,password);
    }

    @Given("^User is logged in to the website with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void userIsLoggedInToTheWebsiteWithUsernameAndPassword(String username, String password) {
        LoginPage.getInstance(mSettings).GoTo();
        LoginPage.getInstance(mSettings).LoginWithUsernameAndPassword(username,password);
    }

    @Given("^User is on the login page of the website$")
    public void userIsOnTheLoginPageOfTheWebsite() {
        LoginPage.getInstance(mSettings).GoTo();
    }

    @Then("^User should see the fields to enter username, password and login button$")
    public void userShouldSeeTheFieldsToEnterUsernamePasswordAndLoginButton() {
        Assert.assertTrue(LoginPage.getInstance(mSettings).IsUserNameVisible(),"Not at Login Page");
        Assert.assertTrue(LoginPage.getInstance(mSettings).IsPasswordVisible(),"Not at Login Page");
        Assert.assertTrue(LoginPage.getInstance(mSettings).IsLoginButtonVisible(),"Not at Login Page");
    }
}
