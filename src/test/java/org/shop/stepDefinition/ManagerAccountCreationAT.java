package org.shop.stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.shop.pages.BasePage;
import org.shop.utilities.JsonUtils;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerAccountCreationAT extends BaseClassAT {

    List<String> actualDropdownText = new ArrayList<>();
    List<String> expectedDropdownText = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    String fullName = "";

    @And("verify dropdown options {string} for {string}")
    public void verifyDropdownOptions(String dropdownLabel, String testcase) throws FileNotFoundException {
        actualDropdownText = BasePage.validateDropdownOptions(BasePage.dropdownElement(driver, dropdownLabel));
        String testcaseName = "";
        String testcaseKey = "";
        if(testcase.contains("."))
        {
            String[] testcaseData = testcase.split("\\.");
            testcaseName = testcaseData[0];
            testcaseKey = testcaseData[1];
        }
        expectedDropdownText = Arrays.asList(JsonUtils.getTestData(testcaseName,testcaseKey).split(","));
        Assert.assertEquals(actualDropdownText, expectedDropdownText);
    }

    @And("select the newly created customer by firstname and lastname for {string}")
    public void selectTheNewlyCreatedCustomerByFirstnameAndLastname(String label,DataTable dataTable) {
        dataTable.asMaps(String.class, String.class).forEach(
                row -> {
                    String key = row.get("key");
                    String value = row.get("value");
                    if(value.startsWith("#") && value.contains("."))
                    {
                        value = JsonUtils.getJsonData(value);
                    }
                    nameList.add(value);
                }
        );
        for (String name:nameList) {
            fullName = STR."\{fullName}\{name} ";
        }
        fullName = fullName.trim();
        BasePage.selectDropdownOption(BasePage.dropdownElement(driver,label),fullName);
    }

    @And("select {string} as dropdown option for {string}")
    public void selectTheDropdownOption(String currencyValue, String currencyDropdown) {
        BasePage.selectDropdownOption(BasePage.dropdownElement(driver,currencyDropdown),currencyValue);
    }
}
