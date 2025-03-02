package org.shop.stepDefinition;

import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.shop.utilities.Log;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class BaseClassAT {
    protected static ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<RemoteWebDriver>();
    public static WebDriver driver;
    public static Capabilities capabilities;
    public static String propertyValue;

    public WebDriver getWebDriver() {
        return remoteDriver.get();
    }

    public static String getProperty(String key) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/properties/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        propertyValue = properties.getProperty(key);
        Log.info("Getting value for the property {}");
        return propertyValue;
    }

    @BeforeMethod
    public static WebDriver openBrowser() throws IOException {
        String browser = getProperty("browser");
        String grid = getProperty("grid");
        if(grid.equals("true")) {
            switch (browser) {
                case "firefox":
                    capabilities = new FirefoxOptions();
                    break;
                case "chrome":
                    capabilities = new ChromeOptions();
                    break;
                case "edge":
                    capabilities = new EdgeOptions();
                    break;
            }
            remoteDriver.set(new RemoteWebDriver(new URL("http://192.168.197.1:4444"),capabilities));
        } else {
            if (browser.equals("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                driver = new ChromeDriver(options);
            } else if (browser.equals("firefox")) {
                driver = new FirefoxDriver();
            } else {
                driver = new EdgeDriver();
            }
        }
        Log.info("Opening the browser");
        return driver;
    }

    public void openApplication() throws IOException {
        openBrowser();
        Log.info("Maximizing the Window");
        driver.manage().window().maximize();
        Log.info("Opening the application");
        driver.get(getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void closeBrowser() {
        Log.info("Closing the browser");
        driver.quit();
    }

}
