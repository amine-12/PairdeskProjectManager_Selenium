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
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class AssignUserSeleniumTest {

    ChromeDriver driver;
    private final String SCREENSHOTS = "./src/test/onDemandFeatureScreenshots/FeatureCreate";

    public AssignUserSeleniumTest(ChromeDriver driver) {
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

    void test_Add_feature() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,30);

        driver.get("http://localhost:8081");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"usernameLogin\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"passwordLogin\"]")).sendKeys("12345678");
        driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@id='featuresNavLink']")).click();
        Thread.sleep(3000);

        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        WebElement newFeatureBTN = driver.findElement(By.xpath("//*[contains(text(),'Add a new feature')]"));
        newFeatureBTN.click();

        WebElement featureName = driver.findElement((By.xpath("//div[@id='inputFeatureName']//input[@id='featureName']")));
        featureName.sendKeys("New Selenium Feature");

        WebElement featureDescription = driver.findElement(By.xpath("//div[@id='inputFeatureDescription']//textarea[@id='description']"));
        featureDescription.sendKeys("New Selenium Feature description");

        Select featurePriority = new Select(driver.findElement(By.xpath("//div[@id='inputFeaturePriority']//select[@id='priority']")));
        featurePriority.selectByVisibleText("Medium");

        WebElement dateBox = driver.findElement(By.xpath("//div[@id='inputFeatureDeadline']//input[@id='deadline']"));
        dateBox.sendKeys("11262023");
        dateBox.sendKeys(Keys.ARROW_RIGHT);
        dateBox.sendKeys("0345PM");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='inputFeatureUser']//select[@id='user']")));

        Select assignedUser = new Select(driver.findElement(By.xpath("//div[@id='inputFeatureUser']//select[@id='user']")));
        assignedUser.selectByVisibleText("admin");

        WebElement submitNewFeature = driver.findElement(By.xpath("//*[contains(text(),'Create Feature')]"));
        submitNewFeature.click();

        Thread.sleep(2000);
        driver.navigate().refresh();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'New Selenium Feature')]")));

        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'New Selenium Feature')]"));
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        String successString = "New Selenium Feature description";

        assertThat(success.getText(), is(successString));
    }

    void Get_Feature_Details() throws InterruptedException{
        test_Add_feature();
        driver.findElement(By.linkText("New Selenium Feature")).click();
        Thread.sleep(2000);
        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'New Selenium Feature')]"));
        String successString = "New Selenium Feature";
        assertThat(success.getText(), is(successString));
    }

    @Test
    void Check_Assigned_User_Test() throws InterruptedException{
        Get_Feature_Details();

        WebElement assignedUser = driver.findElement(By.xpath("//*[contains(text(),'Assigned to: admin')]"));

        String successString = "Assigned to: admin";

        assertThat(assignedUser.getText(), is(successString));

        driver.quit();
    }
}