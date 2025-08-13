package com.example.bookhive.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;
    private final By heading = By.tagName("h1");
    private final By messageParagraph = By.cssSelector("body p");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAtCheckout() {
        return driver.getTitle().contains("Checkout") || driver.findElement(heading).getText().contains("Checkout");
    }

    public boolean hasThankYouMessage() {
        String txt = driver.findElement(messageParagraph).getText();
        return txt != null && txt.toLowerCase().contains("thank you");
    }
}
