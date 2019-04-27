package com.selenium;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {
    private static final long DEFAULT_TIMEOUT_IN_SECS = 5L;

    private final WebDriver driver;
    private WebElement element;
    private List<WebElement> elements;

    public StepDefinitions() {
        File file = new File("E:/ChromeDriver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();

        elements = new ArrayList<>();
    }

    @Given("Open \"(.+)\"")
    public void openUrl(final String url) throws InterruptedException {
        driver.get(url);
    }

    @Then("Find by css selector \"(.+)\"")
    public void findByCssSelector(final String cssSelector) {
        element = new WebDriverWait(driver, DEFAULT_TIMEOUT_IN_SECS)
            .until(driver -> driver.findElement(By.cssSelector(cssSelector)));

        Assert.assertNotNull(element);
    }

    @Then("Find all by css selector \"(.+)\"")
    public void findAllByCssSelector(final String cssSelector) {
        elements = new WebDriverWait(driver, DEFAULT_TIMEOUT_IN_SECS)
            .until(driver -> driver.findElements(By.cssSelector(cssSelector)));

        Assert.assertNotNull(elements);
    }

    @Then("Find by id \"(.+)\"")
    public void findById(final String id) {
        element = new WebDriverWait(driver, DEFAULT_TIMEOUT_IN_SECS)
            .until(driver -> driver.findElement(By.id(id)));

        Assert.assertNotNull(element);
    }

    @Then("Find by xpath \"(.+)\"")
    public void findByXPath(final String xpath) {
        element = new WebDriverWait(driver, DEFAULT_TIMEOUT_IN_SECS)
            .until(driver -> driver.findElement(By.xpath(xpath)));

        Assert.assertNotNull(element);
    }

    @When("I search for \"(.+)\"")
    public void typeTextIntoInputField(final String query) {
        WebElement element = driver.findElement(By.name("q"));
        // Enter something to search for
        element.sendKeys(query);
        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
    }

    @Then("Result href is \"(.+)\"")
    public void checkLinkTitle(final String linkTitle) {
        WebElement result = element.findElement(By.cssSelector("link"));
        Assert.assertNotNull(result);

        Assert.assertEquals(result.getAttribute("href"), linkTitle);

    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}
