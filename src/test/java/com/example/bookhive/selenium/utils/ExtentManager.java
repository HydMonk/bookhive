package com.example.bookhive.selenium.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Paths;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = Paths.get("target", "reports", "TestReport.html").toString();
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
