package ru.stqa.training.selenium.Homework09_02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 20-Mar-17.
 */
public class RemoteLaunchInClouds {
    public static final String USERNAME = "elena364";
    public static final String AUTOMATE_KEY = "veZ6WtqMcLoMK4216syg";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "internet Explorer");
        //caps.setCapability("browser", "chrome");
        //caps.setCapability("browser", "firefox");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("build", "First build");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));

        element.sendKeys("Lena");
        element.submit();

        System.out.println(driver.getTitle());


        driver.quit();

    }


}
