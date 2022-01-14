package seleniumFeatures;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class ProgressBarSeleniumTest {

    ChromeDriver driver;
    private final String SCREENSHOTS = "./src/test/onDemandFeatureScreenshots/FeatureCreate";

    public ProgressBarSeleniumTest(ChromeDriver driver) {
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
    @Test
    void percentageCompleted_test() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,30);

        driver.get("http://localhost:8081");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"usernameLogin\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"passwordLogin\"]")).sendKeys("12345678");
        driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@id='featuresNavLink']")).click();


        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\'editDeleteButtonsDiv\']/a")).click();
        assertTrue(driver.getPageSource().contains("Overall Progress : 0.0 %"));



    }
    @Test
    void progressBar_Test() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,30);

        driver.get("http://localhost:8081");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"usernameLogin\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"passwordLogin\"]")).sendKeys("12345678");
        driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@id='featuresNavLink']")).click();


        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\'editDeleteButtonsDiv\']/a")).click();

        driver.findElement(By.xpath("//*[@id=\'app\']/div[2]/div/div/div[2]/div/div[2]"));
        assertTrue(driver.getPageSource().contains("progress"));



    }

}