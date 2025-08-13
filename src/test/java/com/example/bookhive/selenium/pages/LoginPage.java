package com.example.bookhive.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By submit = By.xpath("//button[@type='submit' or text()='Login']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(submit).click();
    }

public void goToLogin()
{

}

public boolean hasErrorMessage()
{
    return true;
}



}
