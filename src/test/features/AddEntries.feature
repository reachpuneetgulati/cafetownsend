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
    Then Clear data created with firstname as "Ant" and lastname as "Man"

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

    Scenario Outline: Employee with illegal or blank details should not be saved
      Given User has logged in
      And User clicks "Create" button
      Then User is presented with a form to enter employee details
      When User provides firstname as "<firstname>" , lastname as "<lastname>", startdate as "<startdate>" and email as "<email>"
      And User click on Add
      Then Employee is not saved and user stays on add employee screen
      Examples:
        | firstname    | lastname| startdate|email              |
        |              |         |          |                   |
        |              |Last     |2018-07-01|firstlast@gmail.com|
        |First         |         |2018-07-01|firstlast@gmail.com|
        |First         |Last     |          |firstlast@gmail.com|
        |First         |Last     |2018-07-01|                   |
        |First         |Last     |01-07-2018|firstlast@gmail.com|
        |First         |Last     |2018-07-01|firstlast@gmail    |

