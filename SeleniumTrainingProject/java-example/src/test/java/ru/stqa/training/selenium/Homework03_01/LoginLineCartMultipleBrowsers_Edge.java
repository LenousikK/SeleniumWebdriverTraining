package ru.stqa.training.selenium.Homework03_01;

import org.junit.Before;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by LenkaPenka on 26-Feb-17.
 */
public class LoginLineCartMultipleBrowsers_Edge extends LoginLineCartMultipleBrowsers_TestRun_Test_Finish {
    @Before
    public void start() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, 10);
    }
}
