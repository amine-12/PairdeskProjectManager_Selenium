package seleniumLogin;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SeleniumExtension.class)
public class LoginSeleniumTest {
    ChromeDriver driver;

    public LoginSeleniumTest(ChromeDriver driver) {
        this.driver = driver;
    }

    @Test
    void Correct_Login_Credentials(){
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
            TimeUnit.SECONDS.sleep(2);
            String successString = "Overview";
            WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Overview')]"));
            assertThat(success.getText(), is(successString));


        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Test
    void Incorrect_Login_Credentials(){
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(2);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[1]"));
            username.sendKeys("afsddmin");
            TimeUnit.SECONDS.sleep(1);
            WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[2]"));
            password.sendKeys("1678");
            TimeUnit.SECONDS.sleep(1);
            WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/button"));
            login.click();
            TimeUnit.SECONDS.sleep(2);
            String successString = "Wrong Credentials";
            WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Wrong Credentials')]"));
            assertThat(success.getText(), is(successString));


        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Test
    void Missing_Username_Login(){
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(1);
            WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[2]"));
            password.sendKeys("12345678");
            TimeUnit.SECONDS.sleep(1);
            WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/button"));
            login.click();
            TimeUnit.SECONDS.sleep(2);
            String successString = "Username Required";
            WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Username Required')]"));
            assertThat(success.getText(), is(successString));


        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Test
    void Missing_Password_Login(){
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(1);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/input[1]"));
            username.sendKeys("admin");
            TimeUnit.SECONDS.sleep(1);
            WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/button"));
            login.click();
            TimeUnit.SECONDS.sleep(2);
            String successString = "Password Required";
            WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Password Required')]"));
            assertThat(success.getText(), is(successString));


        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Test
    void Missing_Username_Password_Login(){
        try {
            driver.get("http://localhost:8081");
            driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(1);
            WebElement login = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/form/button"));
            login.click();
            TimeUnit.SECONDS.sleep(2);
            String successString1 = "Username Required";
            WebElement success1 = driver.findElement(By.xpath("//*[contains(text(),'Username Required')]"));

            String successString2 = "Password Required";
            WebElement success2 = driver.findElement(By.xpath("//*[contains(text(),'Password Required')]"));
            assertThat(success1.getText(), is(successString1));
            assertThat(success2.getText(), is(successString2));


        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
