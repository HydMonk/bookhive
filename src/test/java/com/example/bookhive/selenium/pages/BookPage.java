package com.example.bookhive.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addToCartBtn = By.id("addToCartBtn");

    public BookPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForAddToCartButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(addToCartBtn));
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }
}
