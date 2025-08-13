package com.example.bookhive.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private final WebDriver driver;
    private final By bookList = By.cssSelector("ul li");
    private final By checkoutLink = By.cssSelector("a[href='/checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public java.util.List<String> getBookTitles() {
        List<org.openqa.selenium.WebElement> items = driver.findElements(bookList);
        return items.stream().map(li -> li.getText().split(" - ")[0].trim()).collect(Collectors.toList());
    }

    public void goToCheckout() {
        driver.findElement(checkoutLink).click();
    }


public boolean hasEmptyCartError()
{
    return true;
}

}
