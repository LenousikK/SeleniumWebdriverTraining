package ru.stqa.training.selenium.Homework10_01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;

import static javax.swing.text.html.CSS.getAttribute;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 23-Mar-17.
 */
public class LiteCartErrorsInBrowserConsole {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testInChrome(){
        //Open LiteCart Catalog Page
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        //Do Login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Catalog | My Store"));
        //Get list of products to be opened
        List<WebElement> listOfProducts = driver.findElements(By.cssSelector("td > a"));
        int numberOfProducts = listOfProducts.size();
        for (int j = 3; j < numberOfProducts; j=j+2)
        {
            List<WebElement> listOfProducts2 = driver.findElements(By.cssSelector("td > a"));
            String productName = listOfProducts2.get(j).getAttribute("textContent");
            String pageName = "Edit Product: " + productName + " | My Store";
            listOfProducts2.get(j).click();
            //Log
            System.out.println(driver.manage().logs().getAvailableLogTypes());

            List<LogEntry> logRecords = driver.manage().logs().get("browser").getAll();
            if (logRecords.size()==0)
                 {
                    System.out.println("No message in Browser Console");
                 }
            else
                {
                    for (int i = 0; i < logRecords.size(); i++) {
                    if ((logRecords.get(i).getLevel().equals(Level.SEVERE)) || (logRecords.get(i).getLevel().equals(Level.WARNING)) || (logRecords.get(i).getLevel().equals(Level.CONFIG)) || (logRecords.get(i).getLevel().equals(Level.INFO))|| (logRecords.get(i).getLevel().equals(Level.FINE)))
                        {
                                System.out.println(logRecords.get(i).getTimestamp() + " " + logRecords.get(i).getLevel() + " " + logRecords.get(i).getMessage());
                        }
                    else
                        {
                             System.out.println(logRecords.get(i).getTimestamp() + " " + "No message in Browser Console");
                         }

                }
            }
            wait.until(titleIs(pageName));

            //Go back
            driver.findElement(By.name("cancel")).click();
            wait.until(titleIs("Catalog | My Store"));
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
