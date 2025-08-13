package com.example.bookhive.selenium.tests;

import com.aventstack.extentreports.Status;
import com.example.bookhive.selenium.BaseTest;
import com.example.bookhive.selenium.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.example.bookhive.selenium.BaseReportTest;

public class SmokeTests extends BaseReportTest {

    @Test
    public void smoke_login_works() {

        test = extent.createTest("Verify Login works");
try{


        HomePage home = new HomePage(driver);
        home.open(baseUrl);

        LoginPage login = new LoginPage(driver);
        home.goToLogin();
        login.login("admin", "admin123");

        Assertions.assertTrue(home.isLoggedIn(), "User should be logged in");

         test.log(Status.PASS, "Home page loaded successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void smoke_search_works() {
        HomePage home = new HomePage(driver);
        home.open(baseUrl);
        home.search("Clean Code");

        SearchPage search = new SearchPage(driver);
        Assertions.assertTrue(
                search.hasResultWithTitle("Clean Code"),
                "Search should return 'Clean Code'"
        );
    }

    @Test
    public void smoke_logout_works() {
        HomePage home = new HomePage(driver);
        home.open(baseUrl);

        LoginPage login = new LoginPage(driver);
        home.goToLogin();
        login.login("admin", "admin123");
        Assertions.assertTrue(home.isLoggedIn(), "User should be logged in before logout");

        home.logout();
        Assertions.assertFalse(home.isLoggedIn(), "User should be logged out");
    }
}
