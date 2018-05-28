Feature: Trial
  As the owner I would like to use the site to create new employee details

  Background: User navigates to login page and logs in
    Given I am logged in to the website with username "Luke" and password "Skywalker"

    Scenario Outline: Test
      Given User has logged in
      Then User makes sure no entry is present with firstname as "<firstname>" and lastname as "<lastname>"
      Examples:
      |firstname|lastname|
      |Ant      |Man     |
      |Fernando |Alonso  |
      |Dummy    |User    |
      |Daniel   |Ricciardo|
