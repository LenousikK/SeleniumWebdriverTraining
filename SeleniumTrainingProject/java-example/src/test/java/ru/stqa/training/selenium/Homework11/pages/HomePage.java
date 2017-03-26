package ru.stqa.training.selenium.Homework11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class HomePage extends Page {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    public WebElement productLinks() {
        List<WebElement> productLinks = driver.findElements(By.cssSelector("#box-most-popular > div > ul > li > a.link"));
        return productLinks.get(0);
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

    public List<WebElement> linkBackToHome() {
        List<WebElement> linkBackToHome = driver.findElements(By.cssSelector("nav#breadcrumbs > ul.list-horizontal > li > a"));
        return linkBackToHome;
    }

}
