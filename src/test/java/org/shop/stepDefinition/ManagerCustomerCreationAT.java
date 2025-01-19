package org.shop.stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.shop.pages.BasePage;
import org.shop.utilities.JsonUtils;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerCustomerCreationAT extends BaseClassAT {

    public static Map<String, String> customerData = new HashMap<String, String>();

    @Given("user navigate to application url")
    public void user_navigate_to_application_url() throws IOException {
        openBrowser();
    }

    @When("login with {string} option")
    public void login_with_option(String option) {
        BasePage.getElementByContainsText(driver, option).click();
    }

    @When("navigate to {string} page")
    public void navigate_to_page(String buttonText) {
        BasePage.getElementByContainsText(driver, buttonText).click();
    }

    @When("user fills the fields:")
    public void user_fills_the_fields(DataTable dataTable) {
        dataTable.asMaps(String.class, String.class).forEach(
                row -> {
                    String key = row.get("key");
                    String value = row.get("value");
                    if(value.startsWith("#") && value.contains("."))
                    {
                        value = JsonUtils.getJsonData(value);
                    }
                    customerData.put(key, value);
                    BasePage.getElementByNgModel(driver,key).sendKeys(value);
                }
        );
    }

    @When("click on {string} button")
    public void click_on_button(String buttonText) {
        BasePage.getElementByText(driver, buttonText).click();
    }

    @Then("get {string} and accept the alert")
    public void getAndAcceptTheAlert(String nameId) {
        String id = BasePage.performAlertActions(driver);
        System.out.println(STR."\{nameId} is : \{id}");
    }

    @Then("verify customer is created")
    public void verify_customer_is_created() {
        String customerName = customerData.get("fName");
        BasePage.scrollToRequiredElement(driver, customerName);
        Assert.assertEquals(BasePage.getElementByText(driver, customerName).getText(), customerName);
    }

    @And("close the browser")
    public void closeTheBrowser() {
        closeBrowser();
    }
}
