


  Feature: User Module Functionality

Background: Admin is on Base URL
Given Admin is on base url with valid auth

@createclass
  Scenario Outline: Creating User with different values from Test data
    Given Admin is on base url with valid auth
    When The Admin sends HTTPS POST request for <scenario> scenarios as input "<Sheetname>" and "<TestCaseID>" for User
    Then The Admin get <scenario> response code and message as "<Sheetname>" and "<TestCaseID>" for User

    Examples: 
      | TestCaseID  | scenario              | Sheetname |
      | TC_01_user  | valid data            | user      |
