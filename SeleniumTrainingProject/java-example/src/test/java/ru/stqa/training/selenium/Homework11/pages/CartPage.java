package ru.stqa.training.selenium.Homework11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends Page {

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement removeButton() {
        WebElement removeButton = driver.findElement(By.name("remove_cart_item"));
        return removeButton;
    }

    public List<WebElement> productsInCart() {
        List<WebElement> productsInCart = driver.findElements(By.cssSelector("ul.shortcuts > li.shortcut > a"));
        return productsInCart;
    }

    public List<WebElement> allTableRows() {
        List<WebElement> allTableRows = driver.findElements(By.cssSelector("table.dataTable tr"));
        return allTableRows;
    }

    public void removeProductsFromCart() {
        productsInCart().get(0).click();
        int numberOfProducts = productsInCart().size();
        System.out.println(numberOfProducts);
        for (int counterRemoval = 0; counterRemoval < numberOfProducts; counterRemoval++) {

            removeButton().click();
            allTableRows();
            wait.until(ExpectedConditions.stalenessOf(allTableRows().get(1)));
            System.out.println(counterRemoval);

        }
    }

}
