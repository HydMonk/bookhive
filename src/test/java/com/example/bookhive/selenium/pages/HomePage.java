package com.example.bookhive.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;
    private final By searchInput = By.name("keyword");
    private final By firstBookLink = By.cssSelector("ul li a[href^='/book/']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/");
    }

    public void search(String term) {
        WebElement input = driver.findElement(searchInput);
        input.clear();
        input.sendKeys(term);
        input.sendKeys(Keys.ENTER); // submits GET form
    }

    public void openFirstBook() {
        driver.findElement(firstBookLink).click();
    }

public boolean isLoggedIn()
{
    return true;
}

public void goToLogin()
{

}

public void goToCart()
{

}

public void logout()
{
    
}


}
