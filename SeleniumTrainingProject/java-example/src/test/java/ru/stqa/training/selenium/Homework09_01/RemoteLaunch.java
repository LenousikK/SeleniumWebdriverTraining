package ru.stqa.training.selenium.Homework09_01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LenkaPenka on 20-Mar-17.
 */
public class RemoteLaunch {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://192.168.100.4:4444/wd/hub"), DesiredCapabilities.internetExplorer());
        wait = new WebDriverWait(driver, 10);


    }

    @Test
    public void testInChrome() {
        driver.get("https://www.google.by/");

    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
