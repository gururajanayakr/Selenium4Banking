package org.shop.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    public static void takeScreenshot(WebDriver driver, String scenarioName) {
        try {
            // Capture screenshot as file
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Define screenshot path
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String screenshotName = STR."screenshots/\{scenarioName}_\{timestamp}.png";
            File destFile = new File(STR."\{System.getProperty("user.dir")}/FailedScreenshots/\{screenshotName}");
            // Save the screenshot
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            System.err.println(STR."Failed to save screenshot: \{e.getMessage()}");
        }
    }
}
