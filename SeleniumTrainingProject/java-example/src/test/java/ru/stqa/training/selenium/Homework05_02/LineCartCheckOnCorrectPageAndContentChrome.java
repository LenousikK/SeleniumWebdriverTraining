package ru.stqa.training.selenium.Homework05_02;

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
 * Created by LenkaPenka on 08-Mar-17.
 */
public class LineCartCheckOnCorrectPageAndContentChrome {
         private WebDriver driver;
         private WebDriverWait wait;

        @Before
        public void start() {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
        }

            @Test
            public void testInChrome() {
                //Open LineCart Home Page
                driver.get("http://localhost/litecart/en/");
                wait.until(titleIs("Online Store | My Store"));
                //Get Campaigns product list
                String cssSelectorName = "#box-campaigns > div > ul > li";
                List<WebElement> allCampaignsProducts = driver.findElements(By.cssSelector("#box-campaigns > div > ul > li"));
                System.out.println(allCampaignsProducts.size());
                //Get product name on Home Page
                String productNameOnHomePage = allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link > div.name")).getAttribute("textContent");
                System.out.println("Home Page - product name: " + productNameOnHomePage);
                //Get regular price on Home Page
                String productRegularPriceOnHomePage = allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link > div.price-wrapper > s")).getAttribute("textContent");
                System.out.println("Home Page - regular price: " + productRegularPriceOnHomePage);
                //Get regular price color on Home Page
                String colorRegularPriceOnHomePage = allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link > div.price-wrapper > s")).getCssValue("color");
                System.out.println("Home Page - regular price - color: " + colorRegularPriceOnHomePage);
                //Get regular price text decoration on Home Page
                String textDecorationRegularPriceOnHomePage = allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link > div.price-wrapper > s")).getCssValue("text-decoration");
                System.out.println("Home Page - regular price - text decoration: " + textDecorationRegularPriceOnHomePage);
                //Assert
                Assert.assertTrue(colorRegularPriceOnHomePage.equals("rgba(119, 119, 119, 1)"));
                Assert.assertTrue(textDecorationRegularPriceOnHomePage.equals("line-through"));
                //Get sale price on Home Page
                String productSalePriceOnHomePage = allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link > div.price-wrapper > strong")).getAttribute("textContent");
                System.out.println("Home Page - regular price: " + productSalePriceOnHomePage);
                //Get sale price color on Home Page
                String colorSalePriceOnHomePage = allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link > div.price-wrapper > strong")).getCssValue("color");
                System.out.println("Home Page - sale price - color: " + colorSalePriceOnHomePage);
                //Get sale price font weight on Home Page
                String fontWeightSalePriceOnHomePage = allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link > div.price-wrapper > strong")).getCssValue("font-weight");
                System.out.println("Home Page - sale price - font weight: " + fontWeightSalePriceOnHomePage);
                //Assert
                Assert.assertTrue(colorSalePriceOnHomePage.equals("rgba(204, 0, 0, 1)"));
                Assert.assertTrue(fontWeightSalePriceOnHomePage.equals("bold"));
                //Get sale price size on Home Page
                Dimension sizeSalePriceOnHomePage = allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link > div.price-wrapper > strong")).getSize();
                int heightSalePriceOnHomePage = sizeSalePriceOnHomePage.getHeight();
                int weightSalePriceOnHomePage = sizeSalePriceOnHomePage.getWidth();
                System.out.println("Home Page - sale price - height: " + heightSalePriceOnHomePage + " size:" + weightSalePriceOnHomePage);
                //Get regular price size on Home Page
                Dimension sizeRegularPriceOnHomePage = allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link > div.price-wrapper > s")).getSize();
                int heightRegularPriceOnHomePage = sizeRegularPriceOnHomePage.getHeight();
                int weightRegularPriceOnHomePage = sizeRegularPriceOnHomePage.getWidth();
                System.out.println("Home Page - regular price - height: " + heightRegularPriceOnHomePage + " size:" + weightRegularPriceOnHomePage);
                //Assert
                Assert.assertTrue(heightSalePriceOnHomePage > heightRegularPriceOnHomePage);
                Assert.assertTrue(weightSalePriceOnHomePage > weightRegularPriceOnHomePage);


                //Go to Product Page
                allCampaignsProducts.get(0).findElement(By.cssSelector(cssSelectorName + "> a.link")).click();
                wait.until(titleIs("Yellow Duck | Subcategory | Rubber Ducks | My Store"));
                //Get product name on Product Page
                String productNameOnProductPage = driver.findElement(By.cssSelector("#box-product > div > h1.title")).getAttribute("textContent");
                System.out.println("Product Page - product name: " + productNameOnProductPage);
                //Assert
                Assert.assertTrue(productNameOnHomePage.equals(productNameOnProductPage));
                //Get regular price on Product Page
                String productRegularPriceOnProductPage = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s")).getAttribute("textContent");
                System.out.println("Product Page - regular price: " + productRegularPriceOnProductPage);
                //Get regular price color on Product Page
                String colorRegularPriceOnProductPage = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s")).getCssValue("color");
                System.out.println("Product Page - regular price color: " + colorRegularPriceOnProductPage);
                //Get regular price text decoration on Product Page
                String textDecorationRegularPriceOnProductPage = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s")).getCssValue("text-decoration");
                System.out.println("Product Page - regular price text decoration: " + textDecorationRegularPriceOnProductPage);
                //Assert
                Assert.assertTrue(colorRegularPriceOnProductPage.equals("rgba(102, 102, 102, 1)"));
                Assert.assertTrue(textDecorationRegularPriceOnProductPage.equals("line-through"));
                //Assert
                Assert.assertTrue(productRegularPriceOnHomePage.equals(productRegularPriceOnProductPage));
                //Get sale price on Product Page
                String productSalePriceOnProductPage = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong")).getAttribute("textContent");
                System.out.println("Product Page - regular price: " + productSalePriceOnProductPage);
                //Get sale price color on Product Page
                String colorSalePriceOnProductPage = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong")).getCssValue("color");
                System.out.println("Product Page - sale price - color: " + colorSalePriceOnProductPage);
                //Get sale price font weight on Product Page
                String fontWeightSalePriceOnProductPage = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong")).getCssValue("font-weight");
                System.out.println("Product Page - sale price - font weight: " + fontWeightSalePriceOnProductPage);
                //Assert
                Assert.assertTrue(productSalePriceOnHomePage.equals(productSalePriceOnProductPage));
                //Assert
                Assert.assertTrue(colorSalePriceOnProductPage.equals("rgba(204, 0, 0, 1)"));
                Assert.assertTrue(fontWeightSalePriceOnProductPage.equals("bold"));
                //Get sale price size on Product Page
                Dimension sizeSalePriceOnProductPage = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong")).getSize();
                int heightSalePriceOnProductPage = sizeSalePriceOnProductPage.getHeight();
                int weightSalePriceOnProductPage = sizeSalePriceOnProductPage.getWidth();
                System.out.println("Product Page - sale price - height: " + heightSalePriceOnProductPage + " size:" + weightSalePriceOnProductPage);
                //Get regular price on Product Page
                Dimension sizeRegularPriceOnProductPage = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s")).getSize();
                int heightRegularPriceOnProductPage = sizeRegularPriceOnProductPage.getHeight();
                int weightRegularPriceOnProductPage = sizeRegularPriceOnProductPage.getWidth();
                System.out.println("Product Page - regular price - height: " + heightRegularPriceOnProductPage + " size:" + weightRegularPriceOnProductPage);
                //Assert
                Assert.assertTrue(heightSalePriceOnProductPage > heightRegularPriceOnProductPage);
                Assert.assertTrue(weightSalePriceOnProductPage > weightRegularPriceOnProductPage);

            }

        @After
        public void stop() {
            driver.quit();
            driver = null;
        }
}
