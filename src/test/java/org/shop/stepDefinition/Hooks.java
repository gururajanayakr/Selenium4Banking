package org.shop.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.shop.utilities.Log;
import org.shop.utilities.ScreenshotUtils;

import java.io.IOException;

public class Hooks {
    private static WebDriver driver;

    public Hooks() throws IOException {
        // Initialize the WebDriver instance (can be injected or created here)
        if(driver==null) {
            driver = BaseClassAT.openBrowser();
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            Log.error(STR."Testcase is \{scenario.getName()} failed and screenshot is taken");
            ScreenshotUtils.takeScreenshot(driver, scenario.getName());
        }
    }
}
