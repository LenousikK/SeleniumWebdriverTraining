package ru.stqa.training.selenium.Homework11.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    public WebDriver driver;
    public WebDriverWait wait;
    public Page (WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
}
