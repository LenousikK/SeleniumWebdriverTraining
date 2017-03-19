package ru.stqa.training.selenium.Homework08;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 19-Mar-17.
 */
public class LiteCartLinksInNewWindow {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testInChrome() {
        //Open LiteCart Catalog Page
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        //Do Login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Countries | My Store"));
        //Add New Country
        driver.findElement(By.cssSelector("td > div > a.button")).click();
        wait.until(titleIs("Add New Country | My Store"));
        //Open a new window, switch to it and close it
        List<WebElement> externalLinks = driver.findElements(By.cssSelector("a > i.fa-external-link"));
        for (int counter = 0; counter < externalLinks.size(); counter++)
        {
            String originalWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();
            externalLinks.get(counter).click();
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }

    }

    public ExpectedCondition<String> anyWindowOtherThan(Set <String> originalWindow) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                Set <String> handles = driver.getWindowHandles();
                handles.removeAll(originalWindow);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
