package ru.stqa.training.selenium.Homework11.app;

import org.junit.Test;
import ru.stqa.training.selenium.Homework11.tests.BeforeAfter;
import ru.stqa.training.selenium.Homework11.pages.CartPage;
import ru.stqa.training.selenium.Homework11.pages.HomePage;
import ru.stqa.training.selenium.Homework11.pages.ProductPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckoutProducts extends BeforeAfter {


    @Test
    public void testInChrome() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        wait.until(titleIs("Online Store | My Store"));

        ProductPage productPage = new ProductPage(driver, wait);
        for (int counter = 0; counter < 3; counter++) {
            homePage.productLinks().click();

            productPage.addProductToCart(counter);

            productPage.linkBackToHome().click();
            wait.until(titleIs("Online Store | My Store"));
        }

        homePage.cartLink().click();
        wait.until(titleIs("Checkout | My Store"));
        System.out.println("Cart is Opened");

        CartPage cartPage = new CartPage(driver, wait);
        cartPage.removeProductsFromCart();

    }


}
