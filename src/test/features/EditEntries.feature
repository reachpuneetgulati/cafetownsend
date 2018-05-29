Feature: Edit Entries
  As the owner I would like to use the site to edit employee details

  Background: User navigates to login page and logs in
    Given User is logged in to the website with username "Luke" and password "Skywalker"

  Scenario: User should be able to edit employee details
    Given User has logged in
    And User clicks "Create" button
    Then User is presented with a form to enter employee details
    When User provides firstname as "Fernando" , lastname as "Alonso", startdate as "2018-07-01" and email as "lamatador@gmail.com"
    And User click on Add
    Then The employee details are saved and present in the employee list with first name "Fernando", last name "Alonso", start date "2018-07-01" and email as "lamatador@gmail.com"
    When The user clicks on an employee with first name "Fernando" and lastname "Alonso"
    And User clicks "Edit" button
    Then User is shown the edit screen
    When User changes "FirstName" to "Daniel"
    And User changes "LastName" to "Ricciardo"
    And User clicks on Update
    Then The employee details are changed to firstname as "Daniel" and lastname as "Ricciardo"
    Then Clear data created with firstname as "Daniel" and lastname as "Ricciardo"

  Scenario: User should be able to delete employee details through edit page
    Given User has logged in
    Then Clear data created with firstname as "Fernando" and lastname as "Alonso"
    And User clicks "Create" button
    Then User is presented with a form to enter employee details
    When User provides firstname as "Fernando" , lastname as "Alonso", startdate as "2018-07-01" and email as "lamatador@gmail.com"
    And User click on Add
    Then The employee details are saved and present in the employee list with first name "Fernando", last name "Alonso", start date "2018-07-01" and email as "lamatador@gmail.com"
    When The user clicks on an employee with first name "Fernando" and lastname "Alonso"
    And User clicks "Edit" button
    Then User is shown the edit screen
    When User clicks on Delete and "Reject" to delete
    And User goes back from editing page
    Then The employee with details firstname as "Fernando" and lastname as "Alonso" is not deleted
    And User clicks "Edit" button
    When User clicks on Delete and "Accept" to delete
    Then The employee with details firstname as "Fernando" and lastname as "Alonso" is deleted

  Scenario: User should be able to delete employee details through employee list
    Given User has logged in
    Then Clear data created with firstname as "Fernando" and lastname as "Alonso"
    And User clicks "Create" button
    Then User is presented with a form to enter employee details
    When User provides firstname as "Fernando" , lastname as "Alonso", startdate as "2018-07-01" and email as "lamatador@gmail.com"
    And User click on Add
    Then The employee details are saved and present in the employee list with first name "Fernando", last name "Alonso", start date "2018-07-01" and email as "lamatador@gmail.com"
    When The user clicks on an employee with first name "Fernando" and lastname "Alonso"
    And User clicks "Delete" button
    And User selects Cancel when user is shown confirmation to delete employee
    Then The employee with details firstname as "Fernando" and lastname as "Alonso" is not deleted
    When User clicks "Delete" button
    When User selects ok
    Then The employee with details firstname as "Fernando" and lastname as "Alonso" is deleted
