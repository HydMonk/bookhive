package com.example.bookhive.selenium.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class TestUtils {

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = testName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            String screenshotPath = Paths.get("target","reports", "screenshots", screenshotName).toString();
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
