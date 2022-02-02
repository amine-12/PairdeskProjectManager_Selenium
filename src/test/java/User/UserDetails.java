package User;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SeleniumExtension.class)
public class UserDetails {
    ChromeDriver driver;

    public UserDetails(ChromeDriver driver) {
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

    void Add_User() throws InterruptedException{
        Login();
        WebDriverWait wait = new WebDriverWait(driver,30);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@id='usersNavLinkId']")).click();
        Thread.sleep(3000);

        Random random = new Random();
        WebElement newUserBTN = driver.findElement(By.xpath("//*[contains(text(),'Add User')]"));
        newUserBTN.click();

        WebElement userName = driver.findElement((By.xpath("//input[@id='taskName']")));
        userName.sendKeys(random.nextInt(100) + "user");


        WebElement email = driver.findElement((By.xpath("//input[@id='description']")));
        email.sendKeys(random.nextInt(100) + "@email.com");

        WebElement submitNewUser = driver.findElement(By.xpath("//*[contains(text(),'Create User')]"));
        submitNewUser.click();

        Thread.sleep(2000);
        driver.navigate().refresh();

    }

    @Test
    void User_Details() throws InterruptedException {
        Add_User();
        WebElement newUserBTN = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/ul/div[1]/a/li/div[1]"));
        newUserBTN.click();
        Thread.sleep(2000);
        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'User Profile')]"));

        Thread.sleep(2000);

        String successString = "User Profile";

        assertThat(success.getText(), is(successString));
    }

}
