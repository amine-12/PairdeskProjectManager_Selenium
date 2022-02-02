package Invoice;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SeleniumExtension.class)
public class InvoiceTest {
    ChromeDriver driver;

    public InvoiceTest(ChromeDriver driver) {
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
    void Invoice_Details() throws InterruptedException{
        Login();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"invoicesNavLink\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/ul/div[1]/a")).click();
        Thread.sleep(3000);
        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Invoice')]"));
        String successString = "Invoice";

        assertThat(success.getText(), is(successString));
    }

    @Test
    void Invoice_No_Content() throws InterruptedException{
        Login();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"invoicesNavLink\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/ul/div[1]/a")).click();
        Thread.sleep(3000);
        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Invoice')]"));
        String successString = "Invoice";

        assertThat(success.getText(), is(successString));
    }
}
