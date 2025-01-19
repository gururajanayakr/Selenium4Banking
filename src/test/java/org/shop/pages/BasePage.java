package org.shop.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    public WebDriverWait wait;
    public static String id;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("To get Element by contains Text")
    public static WebElement getElementByContainsText(WebDriver driver, String text) {
        String xpathExpression = "//*[contains(text(),'"+text+"')]";
        return driver.findElement(By.xpath(xpathExpression));
    }

    @Step("To get Element by Text")
    public static WebElement getElementByText(WebDriver driver, String text) {
        String xpathExpression = "//*[text()='"+text+"']";
        return driver.findElement(By.xpath(xpathExpression));
    }

    @Step("To get Element by ng-model")
    public static WebElement getElementByNgModel(WebDriver driver, String placeholder) {
        String xpathExpression = "//*[contains(@ng-model,'"+placeholder+"')]";
        return driver.findElement(By.xpath(xpathExpression));
    }

    @Step("To send input text to textbox")
    public void performSendKeysAction(WebElement textbox, String inputText) {
        wait.until(ExpectedConditions.elementToBeClickable(textbox)).clear();
        textbox.sendKeys(inputText);
    }

    @Step("To handle alert box")
    public static String performAlertActions(WebDriver driver) {
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
        WebElement requiredElement = getElementByText(driver, requiredName);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",requiredElement);
    }

    @Step("To get the dropdown element")
    public static WebElement dropdownElement(WebDriver driver, String label) {
       return driver.findElement(By.xpath(STR."//*[contains(text(),'\{label}')]//following-sibling::select"));
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
}
