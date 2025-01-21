#Created By: Gururaja Nayak
  Feature: User initiating the transaction in the account
    Scenario Outline: User initiating the transaction in the account
      Given user navigate to application url
      When login with "Customer Login" option
      And select user for login "Harry Potter"
      And user fills the fields:
      |  key  |  value                 |
      |fName  | #<testCaseName>.fName  |
      |lName  | #<testCaseName>.lName  |
      |postCd | #<testCaseName>.postCd |
      And click on "Add Customer" button
      Then get "customerId" and accept the alert
      And navigate to "Customers" page
      And verify customer is created
      And close the browser

    Examples:
      | testCaseName |
      | createCustomerByManagerOne |
      | createCustomerByManagerTwo |
      | createCustomerByManagerThree |