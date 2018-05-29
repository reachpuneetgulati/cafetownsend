package stepdefs.entries;

import common.Constants;
import common.Settings;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.AddEmployeePage;
import pages.EditEmployeePage;
import pages.LandingPage;
import pages.LoginPage;
import stepdefs.base.BaseStep;

public class Entry extends BaseStep {
    private Settings mSettings;

    public Entry(BaseStep step){
        this.mSettings = step.getmSettings();
    }
    

    @Given("^User has logged in$")
    public void userHasLoggedIn(){
        Assert.assertTrue(LandingPage.getInstance(mSettings).IsCreateButtonVisibleAndEnabled(),"Not logged in successfully");
        Assert.assertTrue(LandingPage.getInstance(mSettings).IsEditButtonVisible(),"Not logged in successfully");
        Assert.assertTrue(LandingPage.getInstance(mSettings).IsDeleteButtonVisible(),"Not logged in successfully");
    }

    @And("^User clicks \"([^\"]*)\" button$")
    public void userClicksButton(String option) {
        if (option.equalsIgnoreCase(Constants.UserOptions.CREATE.name())){
            LandingPage.getInstance(mSettings).StartCreatingEmployee();
        }else if (option.equalsIgnoreCase(Constants.UserOptions.EDIT.name())){
            LandingPage.getInstance(mSettings).StartEditingEmployee();
        }else if (option.equalsIgnoreCase(Constants.UserOptions.DELETE.name())){
            LandingPage.getInstance(mSettings).ClickDeleteButton();
        }
    }

    @Then("^User is presented with a form to enter employee details$")
    public void userIsPresentedWithAFormToEnterEmployeeDetails() {
        Assert.assertTrue(AddEmployeePage.getInstance(mSettings).IsAddFirstNameVisible(),"Add first name field not visible");
        Assert.assertTrue(AddEmployeePage.getInstance(mSettings).IsAddLastNameVisible(),"Add first name field not visible");
        Assert.assertTrue(AddEmployeePage.getInstance(mSettings).IsAddStartDateVisible(),"Add first name field not visible");
        Assert.assertTrue(AddEmployeePage.getInstance(mSettings).IsAddEmailVisible(),"Add first name field not visible");
    }

    @And("^User click on Add$")
    public void userClickOnAdd() {
        AddEmployeePage.getInstance(mSettings).ClickAddButton();
    }

    @When("^User provides firstname as \"([^\"]*)\" , lastname as \"([^\"]*)\", startdate as \"([^\"]*)\" and email as \"([^\"]*)\"$")
    public void userProvidesFirstnameAsLastnameAsStartdateAsAndEmailAs(String fname, String lname, String sdate, String email){
        AddEmployeePage.getInstance(mSettings).AddFirstName(fname);
        AddEmployeePage.getInstance(mSettings).AddLastName(lname);
        AddEmployeePage.getInstance(mSettings).AddStartDate(sdate);
        AddEmployeePage.getInstance(mSettings).AddEmail(email);
    }


    @Then("^The employee details are saved and present in the employee list with first name \"([^\"]*)\", last name \"([^\"]*)\", start date \"([^\"]*)\" and email as \"([^\"]*)\"$")
    public void theEmployeeDetailsAreSavedAndPresentInTheEmployeeListWithFirstNameLastNameStartDateAndEmailAs(String fname, String lname, String startdate, String email){
        Assert.assertTrue(LandingPage.getInstance(mSettings).VerifyEmployeePresent(fname,lname,startdate,email),"Error creating the employee");
    }

    @Then("^Only one employee is saved with the firstname \"([^\"]*)\" and lastname \"([^\"]*)\"$")
    public void onlyOneEmployeeIsSavedWithTheFirstnameAndLastname(String fname, String lname) {
        Assert.assertTrue(LandingPage.getInstance(mSettings).OnlyOneEmployeeExistsWithName(fname.concat(" ").concat(lname)),"Two users created with the same details");
    }


    @And("^User clicks on Update$")
    public void userClicksOnUpdate() {
        EditEmployeePage.getInstance(mSettings).ClickUpdateButton();
    }

    @When("^The user clicks on an employee with first name \"([^\"]*)\" and lastname \"([^\"]*)\"$")
    public void theUserClicksOnAnEmployeeWithFirstNameAndLastname(String fname, String lname){
        LandingPage.getInstance(mSettings).ClickOnEmployeeInListWithName(fname.concat(" ").concat(lname));
    }

    @Then("^User is shown the edit screen$")
    public void userIsShownTheEditScreen() {
        Assert.assertTrue(EditEmployeePage.getInstance(mSettings).VerifyUpdateButtonPresent(),"Update button not present");
        Assert.assertTrue(EditEmployeePage.getInstance(mSettings).VerifyDeleteButtonPresent(),"Update button not present");
    }

    @When("^User changes \"([^\"]*)\" to \"([^\"]*)\"$")
    public void userChangesTo(String field, String value){
        if(field.equals("FirstName")){
            EditEmployeePage.getInstance(mSettings).AddFirstName(value);
        }else if (field.equals("LastName")){
            EditEmployeePage.getInstance(mSettings).AddLastName(value);
        }else if (field.equals("Email")){
            EditEmployeePage.getInstance(mSettings).AddEmail(value);
        }
    }

    @Then("^The employee details are changed to firstname as \"([^\"]*)\" and lastname as \"([^\"]*)\"$")
    public void theEmployeeDetailsAreChangedToFirstnameAsAndLastnameAs(String fname, String lname) {
        Assert.assertTrue(LandingPage.getInstance(mSettings).VerifyEmployeePresent(fname,lname,null,null),"Error creating the employee");
    }

    @When("^User clicks on Delete and \"([^\"]*)\" to delete$")
    public void userClicksOnDeleteAndToDelete(String action) {
        if (action.equals("Accept")){
            EditEmployeePage.getInstance(mSettings).DeleteAndAccept();
        }else if (action.equals("Reject")){
            EditEmployeePage.getInstance(mSettings).DeleteAndReject();
        }
    }

    @Then("^The employee with details firstname as \"([^\"]*)\" and lastname as \"([^\"]*)\" is not deleted$")
    public void theEmployeeWithDetailsFirstnameAsAndLastnameAsIsNotDeleted(String fname, String lname){
        Assert.assertTrue(LandingPage.getInstance(mSettings).VerifyEmployeePresent(fname, lname,null,null),"Employee got deleted");
    }

    @Then("^The employee with details firstname as \"([^\"]*)\" and lastname as \"([^\"]*)\" is deleted$")
    public void theEmployeeWithDetailsFirstnameAsAndLastnameAsIsDeleted(String fname, String lname) {
        LandingPage.getInstance(mSettings).LogOut();
        LoginPage.getInstance(mSettings).LoginWithUsernameAndPassword("Luke","Skywalker");
        Assert.assertTrue(LandingPage.getInstance(mSettings).VerifyEmployeeNotPresent(fname.concat(" ").concat(lname)),"Employee still present");
    }

    @And("^User goes back from editing page$")
    public void userGoesBackFromEditingPage() {
        EditEmployeePage.getInstance(mSettings).ClickBackButton();
    }

    @When("^User selects ok$")
    public void userSelectsOk() {
        LandingPage.getInstance(mSettings).OKDeleteConfirmation();
    }

    @And("^User selects Cancel when user is shown confirmation to delete employee$")
    public void userSelectsCancelWhenUserIsShownConfirmationToDeleteEmployee() {
        LandingPage.getInstance(mSettings).CancelDeleteConfirmation();
    }

    @And("^Clear data created with firstname as \"([^\"]*)\" and lastname as \"([^\"]*)\"$")
    public void clearDataCreatedWithFirstnameAsAndLastnameAs(String fname, String lname){
        LandingPage.getInstance(mSettings).LogOut();
        LoginPage.getInstance(mSettings).LoginWithUsernameAndPassword("Luke","Skywalker");
        LandingPage.getInstance(mSettings).DeleteRecordsWithName(fname.concat(" ").concat(lname));
    }

    @Then("^Employee is not saved and user stays on add employee screen$")
    public void employeeIsNotSavedAndUserStaysOnAddEmployee() {
        Assert.assertTrue(AddEmployeePage.getInstance(mSettings).IsAddEmailVisible(),"Employee details saved when they shouldn't");
    }
}
