#Created By: Gururaja Nayak
Feature: Manager Creating the account for the Customer
  Scenario Outline: Creating Account for the Customer through Manager Login
    Given user navigate to application url
    When login with "Bank Manager Login" option
    And navigate to "Add Customer" page
    And user fills the fields:
      |  key  |  value                 |
      |fName  | #<testCaseName>.fName  |
      |lName  | #<testCaseName>.lName  |
      |postCd | #<testCaseName>.postCd |
    And click on "Add Customer" button
    Then get "customerId" and accept the alert
    And navigate to "Open Account" page
    And verify dropdown options "Customer" for "<testCaseName>.customerDropdown"
    And select the newly created customer by firstname and lastname for "Customer"
      |  key  |  value                 |
      |fName  | #<testCaseName>.fName  |
      |lName  | #<testCaseName>.lName  |
    And verify dropdown options "Currency" for "<testCaseName>.currencyDropdown"
    And select "Dollar" as dropdown option for "Currency"
    And click on "Process" button
    Then get "accountId" and accept the alert
    And close the browser

    Examples:
      | testCaseName |
      | createAccountByManagerOne |
      | createAccountByManagerTwo |
      | createAccountByManagerThree |