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
public class CreateUser {
    ChromeDriver driver;

    public CreateUser(ChromeDriver driver) {
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
    void Add_User() throws InterruptedException{
        Login();
        WebDriverWait wait = new WebDriverWait(driver,30);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@id='usersNavLinkId']")).click();
        Thread.sleep(3000);


        WebElement newUserBTN = driver.findElement(By.xpath("//*[contains(text(),'Add User')]"));
        newUserBTN.click();

        WebElement userName = driver.findElement((By.xpath("//input[@id='taskName']")));
        userName.sendKeys("New Selenium User");

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        WebElement email = driver.findElement((By.xpath("//input[@id='description']")));
        email.sendKeys(generatedString + "@email.com");

        WebElement submitNewUser = driver.findElement(By.xpath("//*[contains(text(),'Create User')]"));
        submitNewUser.click();

        Thread.sleep(2000);
        driver.navigate().refresh();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'New Selenium User')]")));

        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'New Selenium User')]"));
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        String successString = "New Selenium User";

        assertThat(success.getText(), is(successString));
    }

    @Test
    void Add_User_Missing_Fields() throws InterruptedException{
        Login();
        WebDriverWait wait = new WebDriverWait(driver,30);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@id='usersNavLinkId']")).click();
        Thread.sleep(3000);

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        WebElement email = driver.findElement((By.xpath("//input[@id='description']")));

        WebElement newUserBTN = driver.findElement(By.xpath("//*[contains(text(),'Add User')]"));
        newUserBTN.click();

        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Username Required')]"));
        WebElement success2 = driver.findElement(By.xpath("//*[contains(text(),'Email Required')]"));
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        String successString = "Username Required";
        String successString2 = "Email Required";

        assertThat(success.getText(), is(successString));
        assertThat(success2.getText(), is(successString2));
    }
    @Test
    void Add_User_Missing_Email() throws InterruptedException{
        Login();
        WebDriverWait wait = new WebDriverWait(driver,30);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@id='usersNavLinkId']")).click();
        Thread.sleep(3000);

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        WebElement email = driver.findElement((By.xpath("//input[@id='description']")));

        WebElement newUserBTN = driver.findElement(By.xpath("//*[contains(text(),'Add User')]"));
        newUserBTN.click();

        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Username Required')]"));
        WebElement success2 = driver.findElement(By.xpath("//*[contains(text(),'Email Required')]"));
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        String successString = "Username Required";
        String successString2 = "Email Required";

        assertThat(success.getText(), is(successString));
        assertThat(success2.getText(), is(successString2));
    }
}
