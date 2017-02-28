package ru.stqa.training.selenium.Homework03_03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 26-Feb-17.
 */
public class LoginLineCartFireFoxNightly {
    private WebDriver driver;
    private WebDriverWait wait;

    //FireFox browser
    @Before
    public void start() {
        DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, false);
        FirefoxBinary bin = new FirefoxBinary(new File("C:\\Program Files\\Nightly\\firefox.exe"));
        driver = new FirefoxDriver(bin, new FirefoxProfile(), caps);

        System.out.println((((HasCapabilities) driver).getCapabilities().getBrowserName()) + " " + (((HasCapabilities) driver).getCapabilities().getVersion()));
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
