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

@ExtendWith(SeleniumExtension.class)
public class UpdateFeatureSeleniumTest {

    ChromeDriver driver;
    private final String SCREENSHOTS = "./src/test/onDemandFeatureScreenshots/FeatureCreate";

    public UpdateFeatureSeleniumTest(ChromeDriver driver) {
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
    public void test_Updated_feature() throws InterruptedException {

        driver.get("http://localhost:8081/#/features");
        driver.manage().window().maximize();

        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        WebElement updateFeatureBTN = driver.findElement(By.xpath("//div[@id='editDeleteButtonsDiv']//button[@id='editButton']"));
        updateFeatureBTN.click();

        WebElement featureName = driver.findElement((By.xpath("//div[@id='inputUpdateFeatureName']//input[@id='featureName']")));
        featureName.sendKeys("Updated Selenium Feature");

        WebElement featureDescription = driver.findElement(By.xpath("//div[@id='inputUpdateFeatureDescription']//textarea[@id='description']"));
        featureDescription.sendKeys("Updated Selenium Feature description");

        Select featurePriority = new Select(driver.findElement(By.xpath("//div[@id='inputUpdateFeaturePriority']//select[@id='priority']")));
        featurePriority.selectByVisibleText("High");

        WebElement dateBox = driver.findElement(By.xpath("//div[@id='inputUpdateFeatureDeadline']//input[@id='deadline']"));
        dateBox.sendKeys("11262023");
        dateBox.sendKeys(Keys.ARROW_RIGHT);
        dateBox.sendKeys("0345PM");

        WebElement submitNewFeature = driver.findElement(By.xpath("//*[contains(text(),'Update Feature')]"));
        submitNewFeature.click();

        Thread.sleep(2000);
        driver.navigate().refresh();

        Thread.sleep(2000);

        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'Updated Selenium Feature')]"));

        String successString = "Updated Selenium Feature description";

        assertThat(success.getText(), is(successString));

        driver.quit();
    }
}