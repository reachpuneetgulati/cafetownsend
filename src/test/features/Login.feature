Feature: Login
  As a user of the website, I want to login and access the functions offered by the website
  It has to be made sure that only a correct combination of the username and password allows
  someone to access the functions.

  Background: User navigates to login page
    Given User is on the login page of the website
    Then User should see the fields to enter username, password and login button

  Scenario: Successful login with correct user name and password
    When I fill "Username" with "Luke"
    And I fill "Password" with "Skywalker"
    And  I press the Login button
    Then I should see greeting message
    And I should see a list of names
    And I should see Create, Edit and Delete buttons
    And I should see Logout button

   Scenario Outline:Failed login using wrong credentials
     When I fill "Username" with "<username>"
     And I fill "Password" with "<password>"
     And I press the Login button
     And I should see "<warning>" message
     Examples:
       | username    | password   | warning                           |
       | luke        | Skywalker  | Invalid username or password!     |
       | Luke        | skywalker  | Invalid username or password!     |
       | Luke        |            | Please fill out this field.       |
       |             |            | Please fill out this field.       |
       |             | Skywalker  | Please fill out this field.       |
