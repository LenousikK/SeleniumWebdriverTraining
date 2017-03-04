package ru.stqa.training.selenium.Homework04_01;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 04-Mar-17.
 */
public class LineCartSurfingAdminPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {

        //Open LineCart Login Page
        driver.get("http://localhost/litecart/admin/");

        //Do Login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        //driver.findElement(By.cssSelector("#box-login > form > div.footer > button")).click();
        //driver.findElement(By.xpath("//*[@id=\"box-login\"]/form/div[2]/button")).click();

        //click() = sendKeys(Keys.ENTER)
        System.out.println((((HasCapabilities) driver).getCapabilities().getBrowserName()) + " " + (((HasCapabilities) driver).getCapabilities().getVersion()));
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait.until(titleIs("My Store"));

        List<WebElement> ul = driver.findElements(By.cssSelector("ul.list-vertical > li"));
        int size = ul.size();
        for (int i=1; i<size+1; i++)
        {
            String cssSelector = "ul.list-vertical > li:nth-child(" + i + ")";
            driver.findElement(By.cssSelector(cssSelector)).click();
            Assert.assertTrue(driver.findElements(By.cssSelector("#content > h1")).size() != 0);

            List<WebElement> ulInside = driver.findElements(By.cssSelector(cssSelector + " ul.docs > li"));
            int sizeInside = ulInside.size();
            for (int j=1; j<sizeInside+1; j++)
            {
                String cssSelectorInside = cssSelector + " ul.docs > li:nth-child(" + j + ")";
                driver.findElement(By.cssSelector(cssSelectorInside)).click();
                Assert.assertTrue(driver.findElements(By.cssSelector("#content > h1")).size() != 0);

            }

        }


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
