package org.shop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BankingHomePage extends BasePage {

    public WebDriver driver;

    @FindBy(xpath="//a[text()='Sample Form']")
    private WebElement sampleFormButton;

    @FindBy(xpath="//a[text()='Customer Login']")
    private WebElement customerLoginButton;

    @FindBy(xpath="//a[text()='Bank Manager Login']")
    private WebElement bankManagerLoginButton;

    @FindBy(xpath="//a[text()='Lifetime Membership']")
    private WebElement lifetimeMembershipButton;

    public BankingHomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void testLogin() {

    }

}
