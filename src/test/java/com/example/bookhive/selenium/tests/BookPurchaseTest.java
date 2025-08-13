package com.example.bookhive.selenium.tests;

import com.example.bookhive.selenium.BaseTest;
import com.example.bookhive.selenium.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.bookhive.selenium.BaseReportTest; // import the base class

import java.time.Duration;

public class BookPurchaseTest extends BaseReportTest {

    @Test
    public void purchaseFlow_searchAddToCart_thenLoginAndCheckout() {
        try {
            HomePage home = new HomePage(driver);
            home.open(baseUrl);

            // 1) Basic smoke
            Assertions.assertTrue(driver.getTitle().toLowerCase().contains("bookhive")
                    || driver.getPageSource().contains("Welcome to BookHive"),
                    "Home page should load");

            // 2) Search
            home.search("Clean Code");

            SearchPage search = new SearchPage(driver);
            // assert at least one result contains "Clean Code"
            boolean has = search.resultTitles().stream().anyMatch(t -> t.toLowerCase().contains("clean code"));
            Assertions.assertTrue(has, "Search results should contain 'Clean Code'");

            // 3) Open first result and add to cart
            search.openResultByTitle("Clean Code");
            BookPage book = new BookPage(driver);
            book.addToCart();

            // 4) Verify cart
            CartPage cart = new CartPage(driver);
            // since addToCart redirected to /cart, check presence
            Assertions.assertTrue(cart.getBookTitles().stream().anyMatch(t -> t.toLowerCase().contains("clean code")),
                    "Cart should contain 'Clean Code'");

            // 5) Proceed to checkout — should redirect to login if not authenticated
            cart.goToCheckout();

            // 6) Login
            LoginPage login = new LoginPage(driver);
            login.login("admin", "admin123"); // matches SecurityConfig we've set

            // 7) Verify checkout page
            CheckoutPage checkout = new CheckoutPage(driver);
            Assertions.assertTrue(checkout.isAtCheckout(), "Should be on checkout page");
            Assertions.assertTrue(checkout.hasThankYouMessage(), "Checkout page should display thank-you text");
        } catch (Exception e) {
            throw e;
            }
                }
}
