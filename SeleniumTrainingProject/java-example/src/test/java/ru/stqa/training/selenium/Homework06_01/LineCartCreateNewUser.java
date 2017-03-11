package ru.stqa.training.selenium.Homework06_01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.UUID;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 11-Mar-17.
 */
    public class LineCartCreateNewUser {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testInChrome(){
        //Open LineCart Home Page
        driver.get("http://localhost/litecart/en/");
        wait.until(titleIs("Online Store | My Store"));
        //Open Create Account Page
        driver.findElement(By.cssSelector("#box-account-login > div > form > table > tbody > tr > td > a")).click();
        wait.until(titleIs("Create Account | My Store"));

        //Get input field list
        List<WebElement> inputFieldList = driver.findElements(By.cssSelector("#create-account > div > form > table > tbody > tr > td > input"));
        //Populate input fields with values
        inputFieldList.get(2).sendKeys("FirstName");
        inputFieldList.get(3).sendKeys("LastName");
        inputFieldList.get(4).sendKeys("Test");
        inputFieldList.get(6).sendKeys("12345");
        inputFieldList.get(7).sendKeys("Test");
        WebElement selectorForCountry = driver.findElement(By.cssSelector("span.select2-selection__rendered"));
        selectorForCountry.click();
        driver.findElement(By.xpath("//li[text()='United States']")).click();
        String login = "testingtwx" + UUID.randomUUID() + "@gmail.com";
        driver.findElement(By.name("email")).sendKeys(login);
        inputFieldList.get(10).sendKeys("123456789");
        inputFieldList.get(11).sendKeys("123456");
        inputFieldList.get(12).sendKeys("123456");
        driver.findElement(By.name("create_account")).click();
        //Do logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        System.out.println("The first Logout is done!");
        //Do Login
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(login);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[type=submit][name=login]")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        System.out.println("The second Logout is done!");



    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
