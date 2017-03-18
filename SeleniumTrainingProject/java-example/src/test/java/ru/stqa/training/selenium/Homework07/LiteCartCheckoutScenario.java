package ru.stqa.training.selenium.Homework07;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by LenkaPenka on 18-Mar-17.
 */
public class LiteCartCheckoutScenario {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testInChrome(){
        //Open Home Page
        driver.get("http://localhost/litecart/en/");
        wait.until(titleIs("Online Store | My Store"));

        for (int counter = 0; counter<3; counter++) {
            addProductToCart(counter);
        }
        //Open Cart
        driver.findElement(By.cssSelector("div#cart > a.link")).click();
        wait.until(titleIs("Checkout | My Store"));
        System.out.println("Cart is Opened");
        //Remove Products
        List<WebElement> productIcons = driver.findElements(By.cssSelector("ul.shortcuts > li.shortcut > a"));
        productIcons.get(0).click();
        int numberOfProductIcons = productIcons.size();
        System.out.println(numberOfProductIcons);
        for (int counterRemoval = 0; counterRemoval < numberOfProductIcons; counterRemoval++) {
            driver.findElement(By.name("remove_cart_item")).click();
            List<WebElement> allTableRows = driver.findElements(By.cssSelector("table.dataTable tr"));
            wait.until(ExpectedConditions.stalenessOf(allTableRows.get(1)));
            System.out.println(counterRemoval);

        }

    }

    private void addProductToCart(int counter) {
        List<WebElement> firstProduct = driver.findElements(By.cssSelector("#box-most-popular > div > ul > li > a.link"));
        firstProduct.get(0).click();
        System.out.println("Product # " + (counter +1) + " Opened");
        //Adding to Cart
        try {
            WebElement selectBox = driver.findElement(By.cssSelector("td.options > select"));
            if (selectBox.isDisplayed()) {
                selectBox.click();
                driver.findElement(By.xpath("//option[text()='Small']")).click();
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("No Size combo-box");
        }


        driver.findElement(By.name("add_cart_product")).click();
        System.out.println("Product added to Cart");
        WebElement cartQuantity = driver.findElement(By.cssSelector("#cart > a.content > span.quantity"));
        wait.until(textToBePresentInElement(cartQuantity, Integer.toString(counter+1)));
        System.out.println("Quantity is compared!\n");

        //Go Home
        List<WebElement> linkHome = driver.findElements(By.cssSelector("nav#breadcrumbs > ul.list-horizontal > li > a"));
        linkHome.get(0).click();
        wait.until(titleIs("Online Store | My Store"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
