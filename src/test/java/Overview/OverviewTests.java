package Overview;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SeleniumExtension.class)
public class OverviewTests {
    ChromeDriver driver;

    public OverviewTests(ChromeDriver driver) {
        this.driver = driver;
    }
    void Login(){
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(2);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[1]"));
            username.sendKeys("admin");
            TimeUnit.SECONDS.sleep(1);
            WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[2]"));
            password.sendKeys("12345678");
            TimeUnit.SECONDS.sleep(1);
            WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/button"));
            login.click();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Test
    void Features_Overview() throws InterruptedException{
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(2);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[1]"));
            username.sendKeys("admin");
            TimeUnit.SECONDS.sleep(1);
            WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[2]"));
            password.sendKeys("12345678");
            TimeUnit.SECONDS.sleep(1);
            WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/button"));
            login.click();
        }catch (InterruptedException e){
            e.printStackTrace();
        }TimeUnit.SECONDS.sleep(2);
        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Features Overview')]"));
        String successString = "Features Overview";

        assertThat(success.getText(), is(successString));
    }

    @Test
    void Features_User() throws InterruptedException{
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(2);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[1]"));
            username.sendKeys("admin");
            TimeUnit.SECONDS.sleep(1);
            WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[2]"));
            password.sendKeys("12345678");
            TimeUnit.SECONDS.sleep(1);
            WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/button"));
            login.click();
        }catch (InterruptedException e){
            e.printStackTrace();
        }TimeUnit.SECONDS.sleep(2);
        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Features Assigned to you')]"));
        String successString = "Features Assigned to you";

        assertThat(success.getText(), is(successString));
    }

    @Test
    void Features_Overview_Schedule() throws InterruptedException{
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(2);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[1]"));
            username.sendKeys("admin");
            TimeUnit.SECONDS.sleep(1);
            WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[2]"));
            password.sendKeys("12345678");
            TimeUnit.SECONDS.sleep(1);
            WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/button"));
            login.click();
        }catch (InterruptedException e){
            e.printStackTrace();
        }TimeUnit.SECONDS.sleep(2);
        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Features Overview Schedule')]"));
        String successString = "Features Overview Schedule";

        assertThat(success.getText(), is(successString));
    }

    @Test
    void Features_User_Schedule() throws InterruptedException{
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(2);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[1]"));
            username.sendKeys("admin");
            TimeUnit.SECONDS.sleep(1);
            WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[2]"));
            password.sendKeys("12345678");
            TimeUnit.SECONDS.sleep(1);
            WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/button"));
            login.click();
        }catch (InterruptedException e){
            e.printStackTrace();
        }TimeUnit.SECONDS.sleep(2);
        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Schedule of Features Assigned to you')]"));
        String successString = "Schedule of Features Assigned to you";

        assertThat(success.getText(), is(successString));
    }
}
