#Created By: Gururaja Nayak
  Feature: Manager Creating the Customer
    Scenario Outline: Creating the Customer through Manager Login
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
      And navigate to "Customers" page
      And verify customer is created
      And close the browser

    Examples:
      | testCaseName |
      | createCustomerByManagerOne |
      | createCustomerByManagerTwo |
      | createCustomerByManagerThree |