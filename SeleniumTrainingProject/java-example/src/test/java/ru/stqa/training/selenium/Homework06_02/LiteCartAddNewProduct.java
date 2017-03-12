package ru.stqa.training.selenium.Homework06_02;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 12-Mar-17.
 */
public class LiteCartAddNewProduct {

        private WebDriver driver;
        private WebDriverWait wait;

        @Before
        public void start() {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
        }

         @Test
        public void testInChrome(){
             //Open LiteCart Catalog Page
             driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");

             //Do Login
             driver.findElement(By.name("username")).sendKeys("admin");
             driver.findElement(By.name("password")).sendKeys("admin");
             driver.findElement(By.name("login")).click();
             wait.until(titleIs("Catalog | My Store"));
                driver.findElement(By.xpath("//a[text()=' Add New Product']")).click();
             //GENERAL
             //Status
             List<WebElement> toggleList = driver.findElements(By.cssSelector("input[data-type=toggle]"));
             isChosen(toggleList.get(0));
             System.out.println("Enabled checkbox is ON");
             // backpack: $x("//label[text()=' Enabled']")

             //Name
             String nameProduct = "NameTest" + UUID.randomUUID();
             driver.findElement(By.name("name[en]")).sendKeys(nameProduct);
             //Code
             driver.findElement(By.name("code")).sendKeys("CodeTest");

             //Female
             isChosen(driver.findElement(By.xpath("//input[@value='1-2']")));
             //Male
             isChosen(driver.findElement(By.xpath("//input[@value='1-1']")));
             //Unisex
             isChosen(driver.findElement(By.xpath("//input[@value='1-3']")));
             //Quantity
             driver.findElement(By.name("quantity")).clear();
             driver.findElement(By.name("quantity")).sendKeys("10");
             System.out.println("Quantity is populated!");
             //Upload Images
             String path = new File("src/test/java/ru/stqa/training/selenium/Homework06_02/Duck.png").getAbsolutePath();
             driver.findElement(By.cssSelector("input[type=file]")).sendKeys(path);
             //Date Valid From
             driver.findElement(By.name("date_valid_from")).sendKeys(Keys.HOME + "1303" + Keys.ARROW_RIGHT + "2017");
             //Date Valid To
             driver.findElement(By.name("date_valid_to")).sendKeys(Keys.HOME + "1303" + Keys.ARROW_RIGHT + "2018");
             driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

             //INFORMATION
             driver.findElement(By.xpath("//a[text()='Information']")).click();
             driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
             //Manufacturer
             driver.findElement(By.name("manufacturer_id")).click();
             driver.findElement(By.xpath("//option[text()='ACME Corp.']")).click();

             //Keywords
             driver.findElement(By.name("keywords")).sendKeys("KeywordsTest");
             //Short Description
             driver.findElement(By.name("short_description[en]")).sendKeys("ShortDescriptionTest");
             //Description
             driver.findElement(By.name("description[en]")).sendKeys("DescriptionTest");
             //Head Title
             driver.findElement(By.name("head_title[en]")).sendKeys("HeadTitleTest");
             //Meta Description
             driver.findElement(By.name("meta_description[en]")).sendKeys("MetaDescriptionTest");

             //PRICES
             driver.findElement(By.xpath("//a[text()='Prices']")).click();
             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
             //Purchase Price - field 1
             driver.findElement(By.name("purchase_price")).clear();
             driver.findElement(By.name("purchase_price")).sendKeys("5");
             //Purchase Price - field 2
             driver.findElement(By.name("purchase_price_currency_code")).click();
             driver.findElement(By.cssSelector("option[value=USD]")).click();
             //Price - USD
             driver.findElement(By.name("prices[USD]")).sendKeys("10");
             //Price Incl. Tax - USD
             driver.findElement(By.name("gross_prices[USD]")).clear();
             driver.findElement(By.name("gross_prices[USD]")).sendKeys("13");
             System.out.println("Everything is populated");
             //Price - EUR

             //Price Incl. Tax - EUR
             //Save
             driver.findElement(By.cssSelector("button[name=save]")).click();
             System.out.println("Everything is saved!");
             //Assert
             String xpathProductName = "//a[text()='" + nameProduct + "']";
             Assert.assertTrue(driver.findElement(By.xpath(xpathProductName)).getAttribute("textContent").equals(nameProduct));


        }
            private void isChosen(WebElement e) {
            if ( !e.isSelected() )
                {
                    e.click();
                }
            }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
