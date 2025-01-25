package org.shop.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.shop.pages.BasePage;
import org.shop.utilities.JsonUtils;
import org.testng.Assert;

import java.io.FileNotFoundException;

public class TransactionByUserAT extends BaseClassAT {

    BasePage basepage = new BasePage(driver);

    @And("select {string} from {string} for login")
    public void selectFromForLogin(String customerName, String label) throws FileNotFoundException {
        String name = BasePage.getJsonTestData(customerName);
        BasePage.selectDropdownOption(BasePage.dropdownElement(driver,label),name);
    }

    @Then("verify {string} button is displayed")
    public void verifyIsDisplayed(String buttonText) {
        Assert.assertTrue(BasePage.getElementByText(driver,buttonText).isDisplayed());
    }

    @And("select the account number {string} from {string}")
    public void selectTheAccountNumberFrom(String accountNumber, String dropdownId) throws FileNotFoundException {
        String accNum  = BasePage.getJsonTestData(accountNumber);
        BasePage.selectDropdownOption(BasePage.dropdownById(driver,dropdownId),accNum);
    }

    @And("verify {string} message")
    public void verifyMessage(String messageText) {
        Assert.assertTrue(BasePage.getElementByContainsText(driver,messageText).isDisplayed());
    }

    @And("Verify the balance amount from {string} is matching with {string}")
    public void verifyTheBalanceAmountFromIsMatchingWith(String actBal, String expBal) throws FileNotFoundException {
        String testcaseName = "";
        String testcaseKey = "";
        if(expBal.contains("."))
        {
            String[] testcaseData = expBal.split("\\.");
            testcaseName = testcaseData[0];
            testcaseKey = testcaseData[1];
        }
        String expectedBalance = JsonUtils.getTestData(testcaseName, testcaseKey);
        String actualBalance = BasePage.getElementByNgHide(driver,actBal).getText();
        Assert.assertEquals(actualBalance,expectedBalance);
    }

    @And("user waits for {string} button")
    public void userWaitsForButton(String text) {
        basepage.waitForAnElement(driver,text);
    }

    @And("user waits for {int} seconds")
    public void userWaitsForSeconds(int waitSec) throws InterruptedException {
        Thread.sleep(waitSec* 1000L);
    }
}
