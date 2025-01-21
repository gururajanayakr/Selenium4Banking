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
import java.util.Map;

public class TransactionByUserAT extends BaseClassAT {

    @And("select user for login {string}")
    public void selectUserForLogin(String customerName) {

    }
}
