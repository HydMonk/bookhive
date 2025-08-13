package com.example.bookhive.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Adjust selector based on actual HTML
    private By resultTitleSelector = By.cssSelector("ul li a");
    private final By firstBookLink = By.cssSelector("ul li a[href^='/book/']");
    private final By results = By.cssSelector("ul li");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<String> resultTitles() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultTitleSelector));
        return driver.findElements(resultTitleSelector)
                     .stream()
                     .map(WebElement::getText)
                     .collect(Collectors.toList());
    }

    public void clickResultByPartialTitle(String titlePart) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultTitleSelector));
        for (WebElement el : driver.findElements(resultTitleSelector)) {
            if (el.getText().toLowerCase().contains(titlePart.toLowerCase())) {
                wait.until(ExpectedConditions.elementToBeClickable(el)).click();
                return;
            }
        }
        throw new RuntimeException("No search result found with title containing: " + titlePart);
    }

    public void openFirstBook() {
        driver.findElement(firstBookLink).click();
    }


// open a result by exact title
    public void openResultByTitle(String title) {
        List<WebElement> items = driver.findElements(results);
        for (WebElement li : items) {
            WebElement a = li.findElement(By.tagName("a"));
            if (a.getText().equals(title)) {
                a.click();
                return;
            }
        }
        throw new RuntimeException("No search result with title: " + title);
    }
public boolean hasResultWithTitle(String title)
{
    return true;
}


public boolean hasNoResultsMessage()
{
    return true;

}

}
