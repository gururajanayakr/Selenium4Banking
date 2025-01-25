package org.shop.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.shop.utilities.ScreenshotUtils;

import java.io.IOException;

public class Hooks {
    private final WebDriver driver;

    public Hooks() throws IOException {
        // Initialize the WebDriver instance (can be injected or created here)
        this.driver = BaseClassAT.openBrowser();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtils.takeScreenshot(driver, scenario.getName());
        }
    }
}
