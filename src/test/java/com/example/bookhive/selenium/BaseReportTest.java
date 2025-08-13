package com.example.bookhive.selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.bookhive.selenium.utils.ExtentManager;
import com.example.bookhive.selenium.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(BaseReportTest.TestResultLogger.class)
public class BaseReportTest extends BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeAll
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @AfterAll
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeEach
    public void setupDriver(TestInfo testInfo) {
        driver = TestUtils.createDriver();
        driver.manage().window().maximize();
        test = extent.createTest(testInfo.getDisplayName());
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ExtendWith(TestResultLogger.class)
    public static class TestResultLogger implements TestWatcher {

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            BaseReportTest instance = (BaseReportTest) context.getRequiredTestInstance();
            instance.test.fail(cause);

            String screenshotPath = TestUtils.takeScreenshot(instance.driver, context.getDisplayName());
            if (screenshotPath != null) {
                try {
                    instance.test.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            BaseReportTest instance = (BaseReportTest) context.getRequiredTestInstance();
            instance.test.pass("Test passed");
        }
    }
}
