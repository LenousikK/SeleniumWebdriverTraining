package ru.stqa.training.selenium.Homework05_01;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.String;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 04-Mar-17.
 */
public class LineCartCountriesAndZones {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {

        //Open LineCart Countries Page
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        //Do Login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //click() = sendKeys(Keys.ENTER)
        System.out.println((((HasCapabilities) driver).getCapabilities().getBrowserName()) + " " + (((HasCapabilities) driver).getCapabilities().getVersion()));
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait.until(titleIs("Countries | My Store"));

        List<WebElement> allRows = driver.findElements(By.cssSelector("tr.row"));
        System.out.println(allRows.size());
        List<String> rowCountriesList = new ArrayList<String>();
        for (int i = 2; i < allRows.size()+2; i++)

        {
            String cssSelector = "table > tbody > tr:nth-child(" + i + ") > td";
            List<WebElement> tdsInRow = driver.findElements(By.cssSelector(cssSelector));
            String countryName = tdsInRow.get(4).getAttribute("textContent");
            //System.out.println(tdsInRow.get(4).getAttribute("textContent"));
            rowCountriesList.add(countryName);
            int zoneQuantity = Integer.parseInt(tdsInRow.get(5).getAttribute("textContent"));
            if (zoneQuantity > 0)
            {
                System.out.println("Zone > 0");
                tdsInRow.get(4).findElement(By.cssSelector("a")).click();
                wait.until(titleIs("Edit Country | My Store"));

                List<String> rowCountriesList2 = new ArrayList<String>();
                List<WebElement> allRows2 = driver.findElements(By.cssSelector("#table-zones > tbody > tr"));
                for (int j = 1; j < allRows2.size()-1; j++)
                {
                    String countryName2= allRows2.get(j).findElement(By.cssSelector("td:nth-child(3)")).getAttribute("textContent");
                    rowCountriesList2.add(countryName2);
                }
                for (int k = 0; k < rowCountriesList2.size()-1; k++)
                {
                    System.out.println(rowCountriesList2.get(k));
                    assertTrue(isGreaterThan(rowCountriesList2.get(k+1), rowCountriesList2.get(k)));
                }
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                wait.until(titleIs("Countries | My Store"));
            }
        }
        System.out.println(rowCountriesList.size());
        for (int i = 0; i < rowCountriesList.size()-1; i++) {

            assertTrue(isGreaterThan(rowCountriesList.get(i+1), rowCountriesList.get(i)));

        }

        //Open Geo Zones Page
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        wait.until(titleIs("Geo Zones | My Store"));
        List<WebElement> allRowsGeoZones = driver.findElements(By.cssSelector("#content > form > table > tbody > tr.row"));
        for (int n = 2; n < allRowsGeoZones.size()+2; n++)
        {
            driver.findElement(By.cssSelector("#content > form > table > tbody > tr.row:nth-child(" + n + ") > td:nth-child(3) a")).click();
            wait.until(titleIs("Edit Geo Zone | My Store"));
            List<String> listZones = new ArrayList<String>();
            List<WebElement> allCountryZones = driver.findElements(By.cssSelector("#table-zones > tbody > tr"));
            for (int m = 1; m < allCountryZones.size()-1; m++)
            {
                String countryZoneName= allCountryZones.get(m).findElement(By.cssSelector("td:nth-child(3)> select > option[selected=selected]")).getAttribute("textContent");
                listZones.add(countryZoneName);
            }
            for (int p = 0; p < listZones.size()-1; p++)
            {
                assertTrue(isGreaterThan(listZones.get(p+1), listZones.get(p)));
            }
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            wait.until(titleIs("Geo Zones | My Store"));

        }

   }

    public static boolean isGreaterThan(String b, String a)
    {
        if (b.compareTo(a) > 0)
            return true;
        else return false;
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

