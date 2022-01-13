package com.example.PairdeskProjectManager_Selenium;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

@ExtendWith(SeleniumExtension.class)
public class FeatureListPageTest {
    ChromeDriver driver;

    public FeatureListPageTest(ChromeDriver driver) {
        this.driver = driver;
    }

    @Test
    void FeatureListPage_Navigation(){
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(2);
            WebElement ele = driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/ul/li[3]/a/a"));
            Actions action = new Actions(driver);
            action.moveToElement(ele).perform();
            TimeUnit.SECONDS.sleep(2);
            driver.findElement((By.linkText("FEATURES"))).click();

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
