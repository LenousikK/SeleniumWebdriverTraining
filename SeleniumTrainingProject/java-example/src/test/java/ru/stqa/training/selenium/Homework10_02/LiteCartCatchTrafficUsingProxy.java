package ru.stqa.training.selenium.Homework10_02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 25-Mar-17.
 */
public class LiteCartCatchTrafficUsingProxy {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        Proxy proxy = new Proxy();
       proxy.setHttpProxy("localhost:8888");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("proxy", proxy);

        driver = new ChromeDriver(caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testInChrome() {
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
