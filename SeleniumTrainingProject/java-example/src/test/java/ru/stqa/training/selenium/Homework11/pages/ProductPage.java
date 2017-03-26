package ru.stqa.training.selenium.Homework11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.Homework11.pages.Page;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    public WebElement cartLink() {
        WebElement cartLink = driver.findElement(By.cssSelector("div#cart > a.link"));
        return cartLink;
    }

    public WebElement selectBox() {
        WebElement selectBox = driver.findElement(By.cssSelector("td.options > select"));
        return selectBox;
    }

    public WebElement selectOptionSize() {
        WebElement selectOptionSize = driver.findElement(By.xpath("//option[text()='Small']"));
        return selectOptionSize;
    }

    public WebElement addButton() {
        WebElement addButton = driver.findElement(By.name("add_cart_product"));
        return addButton;
    }

    public WebElement cartQuantity() {
        WebElement cartQuantity = driver.findElement(By.cssSelector("#cart > a.content > span.quantity"));
        return cartQuantity;
    }

    public WebElement linkBackToHome() {
        List<WebElement> linksBackToHome = driver.findElements(By.cssSelector("nav#breadcrumbs > ul.list-horizontal > li > a"));
        return linksBackToHome.get(0);
    }

    public void addProductToCart(int counter) {
        System.out.println("Product # " + (counter + 1) + " Opened");

        try {
            selectBox();
            if (selectBox().isDisplayed()) {
                selectBox().click();
                selectOptionSize().click();
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("No Size combo-box");
        }

        addButton().click();
        System.out.println("Product added to Cart");

        wait.until(textToBePresentInElement(cartQuantity(), Integer.toString(counter + 1)));
        System.out.println("Quantity is compared!\n");
    }

}
