package seleniumFeatures;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SeleniumExtension.class)
public class AddDeleteUserSeleniumTest {

    ChromeDriver driver;
    private final String SCREENSHOTS = "./src/test/onDemandFeatureScreenshots/FeatureCreate";

    public AddDeleteUserSeleniumTest(ChromeDriver driver) {
        this.driver = driver;

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        System.setProperty("sel.jup.screenshot.at.the.end.of.tests", "whenfailure");
        System.setProperty("sel.jup.screenshot.format", "png");
        System.setProperty("sel.jup.output.folder", "./src/test/onDemandFeatureScreenshots");
    }

    public static void takeSnapShot(WebDriver webDriver, String fileWithPath) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
        //call get Screenshot method to create actual image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile = new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

    void test_Add_User() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,30);

        driver.get("http://localhost:8081");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"usernameLogin\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"passwordLogin\"]")).sendKeys("12345678");
        driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@id='usersNavLinkId']")).click();
        Thread.sleep(3000);

        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        WebElement newUserBTN = driver.findElement(By.xpath("//*[contains(text(),'Add User')]"));
        newUserBTN.click();

        WebElement userName = driver.findElement((By.xpath("//input[@id='taskName']")));
        userName.sendKeys("New Selenium User");

        WebElement email = driver.findElement((By.xpath("//input[@id='description']")));
        email.sendKeys("selenium@email.com");

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
    void test_delete_user() throws InterruptedException{
        test_Add_User();

        WebElement deleteUserBtn = driver.findElement(By.xpath("/html/body/div/div[2]/ul/div[3]/a/li/div[4]/button[2]/img"));//make sure to copy full xpath of an already created added user
        deleteUserBtn.click();

        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        assertFalse(driver.getPageSource().contains("New Selenium User"));
        driver.quit();
    }
}