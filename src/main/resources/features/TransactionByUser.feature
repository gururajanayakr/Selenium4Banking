#Created By: Gururaja Nayak
  Feature: User initiating the transaction in the account
    Scenario Outline: User initiating the transaction in the account
      Given user navigate to application url
      When login with "Customer Login" option
      And select "<testCaseName>.name" from "Your Name" for login
      And click on "Login" button
      And user waits for 2 seconds
      Then verify "Logout" button is displayed
      And select the account number "<testCaseName>.accountNo" from "accountSelect"
      And navigate to "Deposit" page
      And user fills the fields:
        |  key    |  value                         |
        | amount  | #<testCaseName>.depositAmount  |
      And click on "Deposit" button
      And verify "Deposit Successful" message
      And navigate to "Withdrawl" page
      And user waits for 2 seconds
      And user fills the fields:
        |  key    |  value                         |
        | amount  | #<testCaseName>.withdrawlAmount  |
      And click on "Withdraw" button
      And verify "Transaction successful" message
      And navigate to "Transactions" page
      And click on "Back" button
      And Verify the balance amount from "noAccount" is matching with "<testCaseName>.balanceAmount"
      And click on "Logout" button
      And close the browser

    Examples:
      | testCaseName |
      | transactionFromAccountOne |
      | transactionFromAccountTwo |
      | transactionFromAccountThree |