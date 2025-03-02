package org.shop.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.shop.stepDefinition.BaseClassAT;
import org.shop.utilities.JsonUtils;
import org.shop.utilities.Log;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public static String id;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("To get Element by contains Text")
    public static WebElement getElementByContainsText(WebDriver driver, String text) {
        Log.info("Getting the element by contains text");
        String xpathExpression = STR."//*[contains(text(),'\{text}')]";
        return driver.findElement(By.xpath(xpathExpression));
    }

    @Step("To get Element by Text")
    public static WebElement getElementByText(WebDriver driver, String text) {
        Log.info("Getting the element by text");
        String xpathExpression = STR."//*[text()='\{text}']";
        return driver.findElement(By.xpath(xpathExpression));
    }

    @Step("To get Element by Type")
    public static WebElement getElementByType(WebDriver driver, String type) {
        Log.info("Getting the element by type");
        String xpathExpression = STR."//*[text()='\{type}']";
        return driver.findElement(By.xpath(xpathExpression));
    }

    @Step("To get Element by ng-model")
    public static WebElement getElementByNgModel(WebDriver driver, String placeholder) {
        Log.info("Getting the element by ng-model");
        String xpathExpression = STR."//*[contains(@ng-model,'\{placeholder}')]";
        return driver.findElement(By.xpath(xpathExpression));
    }

    @Step("To send input text to textbox")
    public void performSendKeysAction(WebElement textbox, String inputText) {
        Log.info("Enter the value inside the textbox");
        wait.until(ExpectedConditions.elementToBeClickable(textbox)).clear();
        textbox.sendKeys(inputText);
    }

    @Step("To handle alert box")
    public static String performAlertActions(WebDriver driver) {
        Log.info("Handling the alert box");
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        String[] text = alertText.split(":");
        id = text[1];
        alert.accept();
        return id;
    }

    @Step("To scroll till required element")
    public static void scrollToRequiredElement(WebDriver driver, String requiredName) {
        Log.info("scrolling the page till the requires element");
        WebElement requiredElement = getElementByText(driver, requiredName);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",requiredElement);
    }

    @Step("To get the dropdown element")
    public static WebElement dropdownElement(WebDriver driver, String label) {
        Log.info("Handling the dropdown element");
       return driver.findElement(By.xpath(STR."//*[contains(text(),'\{label}')]//following-sibling::select"));
    }

    @Step("To get the dropdown element")
    public static WebElement dropdownById(WebDriver driver, String id) {
        return driver.findElement(By.id(id));
    }

    @Step("To select dropdown option")
    public static void selectDropdownOption(WebElement dropdownElement, String visibleText) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    @Step("To validate the dropdowns")
    public static List<String> validateDropdownOptions(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        List<WebElement> allOptions = select.getOptions();
        List<String> optionText = new ArrayList<String>();
        for(WebElement indOption:allOptions) {
            optionText.add(indOption.getText());
        }
        return optionText;
    }

    @Step("To get Element by ng-show")
    public static WebElement getElementByNgShow(WebDriver driver, String message) {
        String xpathExpression = STR."//*[contains(@ng-show,'\{message}')]";
        return driver.findElement(By.xpath(xpathExpression));
    }

    @Step("To get Element by ng-show")
    public static WebElement getElementByNgHide(WebDriver driver, String message) {
        String xpathExpression = STR."(//*[@ng-hide='\{message}']//child::strong)[2]";
        return driver.findElement(By.xpath(xpathExpression));
    }

    @Step("To wait for an element to display")
    public void waitForAnElement(WebDriver driver, String text) {
        wait.until(ExpectedConditions.visibilityOf(getElementByContainsText(driver,text)));
    }

    @Step("To wait for an element to display")
    public static String getJsonTestData(String text) throws FileNotFoundException {
        String testcaseName = "";
        String testcaseKey = "";
        if(text.contains("."))
        {
            String[] testcaseData = text.split("\\.");
            testcaseName = testcaseData[0];
            testcaseKey = testcaseData[1];
        }
        return JsonUtils.getTestData(testcaseName, testcaseKey);
    }

}
