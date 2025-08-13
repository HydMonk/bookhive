package com.example.bookhive.selenium.tests;

import com.example.bookhive.selenium.BaseTest;
import com.example.bookhive.selenium.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegressionTests extends BaseTest {

    @Test
    public void regression_add_to_cart_and_checkout() {
        HomePage home = new HomePage(driver);
        home.open(baseUrl);
        home.search("Clean Code");

        SearchPage search = new SearchPage(driver);
        search.clickResultByPartialTitle("Clean Code");

        BookPage book = new BookPage(driver);
        book.addToCart();

        CartPage cart = new CartPage(driver);
        Assertions.assertTrue(
                cart.getBookTitles().stream().anyMatch(t -> t.contains("Clean Code")),
                "Cart should contain 'Clean Code'"
        );

        cart.goToCheckout();

        LoginPage login = new LoginPage(driver);
        login.login("admin", "admin123");

        CheckoutPage checkout = new CheckoutPage(driver);
        Assertions.assertTrue(checkout.hasThankYouMessage(), "Checkout should complete");
    }

    @Test
    public void regression_login_with_wrong_password_fails() {
        HomePage home = new HomePage(driver);
        home.open(baseUrl);
        home.goToLogin();

        LoginPage login = new LoginPage(driver);
        login.login("admin", "wrongpass");

        Assertions.assertTrue(login.hasErrorMessage(), "Error message should appear");
    }

    @Test
    public void regression_search_non_existing_book() {
        HomePage home = new HomePage(driver);
        home.open(baseUrl);
        home.search("ThisBookDoesNotExist123");

        SearchPage search = new SearchPage(driver);
        Assertions.assertTrue(search.hasNoResultsMessage(), "Should display no results found");
    }

    @Test
    public void regression_checkout_with_empty_cart_shows_error() {
        HomePage home = new HomePage(driver);
        home.open(baseUrl);
        home.goToCart();

        CartPage cart = new CartPage(driver);
        cart.goToCheckout();

        Assertions.assertTrue(cart.hasEmptyCartError(), "Should prevent checkout with empty cart");
    }
}
