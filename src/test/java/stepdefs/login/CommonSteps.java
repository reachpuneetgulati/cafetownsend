package stepdefs.login;

import common.Settings;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import pages.LoginPage;

public class CommonSteps {
    private Settings mSettings;

    public CommonSteps(Settings settings){
        this.mSettings = settings;
    }
//    @Given("^I am on the login page of the website$")
//    public void goToLoginPagePage(){
//        LoginPage.getInstance(mSettings).GoTo();
//    }

//    @Then("^I should see the fields to enter username, password and login button$")
//    public void iShouldSeeTheFieldsToEnterUsernamePasswordAndLoginButton() {
//        // Write code here that turns the phrase above into concrete actions
//        Assert.assertTrue(LoginPage.getInstance(mSettings).IsUserNameVisible(),"Not at Login Page");
//        Assert.assertTrue(LoginPage.getInstance(mSettings).IsPasswordVisible(),"Not at Login Page");
//        Assert.assertTrue(LoginPage.getInstance(mSettings).IsLoginButtonVisible(),"Not at Login Page");
//    }

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
