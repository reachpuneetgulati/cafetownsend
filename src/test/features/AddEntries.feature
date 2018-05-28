Feature: Add Entries
  As the owner I would like to use the site to create new employee details

  Background: User navigates to login page and logs in
    Given User is logged in to the website with username "Luke" and password "Skywalker"

  Scenario: User creates an employee
    Given User has logged in
    And User clicks "Create" button
    Then User is presented with a form to enter employee details
    When User provides firstname as "Ant" , lastname as "Man", startdate as "2018-07-01" and email as "antman@gmail.com"
    And User click on Add
    Then The employee details are saved and present in the employee list with first name "Ant", last name "Man", start date "2018-07-01" and email as "antman@gmail.com"
    Then User makes sure no entry is present with firstname as "Ant" and lastname as "Man"

  Scenario Outline: Employee should not be created with the same details
    Given User has logged in
    And User clicks "Create" button
    Then User is presented with a form to enter employee details
    When User provides firstname as "<firstname>" , lastname as "<lastname>", startdate as "<startdate>" and email as "<email>"
    And User click on Add
    Then Only one employee is saved with the firstname "<firstname>" and lastname "<lastname>"
    Examples:
      | firstname    | lastname| startdate|email              |
      |Dummy         |User     |2018-07-01|dummyuser@gmail.com|
      |Dummy         |User     |2018-07-01|dummyuser@gmail.com|

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

