package org.shop.stepDefinition;

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
    public Capabilities capabilities;
    public static Logger logger = LogManager.getLogger(BaseClassAT.class);
    public static String propertyValue;

    public WebDriver getWebDriver() {
        return remoteDriver.get();
    }

    public static String getProperty(String key) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/properties/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        propertyValue = properties.getProperty(key);
        logger.info("Getting value for the property {}", key);
        return propertyValue;
    }

    @BeforeMethod
    public void openBrowser() throws IOException {
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
            driver.manage().window().maximize();
            driver.get(getProperty("url"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

}
