package ru.stqa.training.selenium.Homework04_02;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 04-Mar-17.
 */
public class LineCartStickersOnHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {

        //Open LineCart Home Page
        driver.get("http://localhost/litecart/en/");

        System.out.println((((HasCapabilities) driver).getCapabilities().getBrowserName()) + " " + (((HasCapabilities) driver).getCapabilities().getVersion()));
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait.until(titleIs("Online Store | My Store"));

        List<WebElement> li = driver.findElements(By.cssSelector("div > ul > li.product"));
        //Print total the quantity of the ducks on the Home page
        System.out.println("Ducks Total:" + li.size());
        for (int i=0; i< li.size(); i++)
        {
            //Get duck from the array
            WebElement b = li.get(i);
            //Find sticker at the duck
            List<WebElement> c = b.findElements(By.cssSelector("div.sticker"));
            //Define the quantity of the stickers
            int d = c.size();
            //Check that the sticker quantity equals to 1
            Assert.assertTrue(d == 1);
            //Print sticker on each duck
            System.out.println("Sticker on duck #" + (i+1) + "/" + li.size());


        }


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
