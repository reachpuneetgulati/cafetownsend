package stepdefs.login;

import common.Constants;
import common.Settings;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.LandingPage;
import pages.LoginPage;

public class Login {

    private Settings mSettings;

    public Login(Settings settings){
        this.mSettings=settings;
    }


    @When("^I fill \"([^\"]*)\" with \"([^\"]*)\"$")
    public void iFillWith(String field, String value) {
        if (field.equals("Username"))
            LoginPage.getInstance(mSettings).EnterUserName(value);
        else if (field.equals("Password"))
            LoginPage.getInstance(mSettings).EnterPassword(value);
    }

    @And("^I press the Login button$")
    public void iPressTheLoginButton()  {
        LoginPage.getInstance(mSettings).ClickLoginButton();
    }


    @And("^I should see a list of names$")
    public void iShouldSeeAListOfNames() {
        Assert.assertTrue(LandingPage.getInstance(mSettings).IsEmployeeListVisible(),"Not logged in successfully");
    }

    @And("^I should see Logout button$")
    public void iShouldSeeLogoutButton()  {
        Assert.assertTrue(LandingPage.getInstance(mSettings).IsLogoutButtonVisible(),"Not logged in successfully");
    }

    @And("^I should see \"([^\"]*)\" message$")
    public void iShouldSeeMessage(String msg){
        if (msg.equals(Constants.LoginWarning.WRONGCREDENTIALS.getMessage())){
            Assert.assertTrue(LoginPage.getInstance(mSettings).IsInValidLogin(),"Invalid username or password");
        }else if (msg.equals(Constants.LoginWarning.EMPTYVALUE.getMessage())){
            Assert.assertTrue(LoginPage.getInstance(mSettings).IsCredentialNotProvided(),"Please enter some value");
        }
    }

    @Then("^I should see greeting message$")
    public void iShouldSeeGreetingMessage(){
        Assert.assertTrue(LandingPage.getInstance(mSettings).IsGreetingsVisible(),"Not logged in successfully");
    }

    @And("^I should see Create, Edit and Delete buttons$")
    public void iShouldSeeCreateEditAndDeleteButtons() {
        Assert.assertTrue(LandingPage.getInstance(mSettings).IsCreateButtonVisibleAndEnabled(),"Not logged in successfully");
        Assert.assertTrue(LandingPage.getInstance(mSettings).IsEditButtonVisible(),"Not logged in successfully");
        Assert.assertTrue(LandingPage.getInstance(mSettings).IsDeleteButtonVisible(),"Not logged in successfully");
    }
}