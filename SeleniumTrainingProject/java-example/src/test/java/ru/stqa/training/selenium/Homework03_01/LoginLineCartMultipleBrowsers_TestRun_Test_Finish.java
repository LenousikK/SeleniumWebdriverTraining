package ru.stqa.training.selenium.Homework03_01;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 26-Feb-17.
 */
public class LoginLineCartMultipleBrowsers_TestRun_Test_Finish {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);

        System.out.println((((HasCapabilities) driver).getCapabilities().getBrowserName()) + " " + (((HasCapabilities) driver).getCapabilities().getVersion()));
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait.until(titleIs("My Store"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
