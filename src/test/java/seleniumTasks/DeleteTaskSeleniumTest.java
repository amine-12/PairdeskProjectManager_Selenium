package seleniumTasks;


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
public class DeleteTaskSeleniumTest {

    ChromeDriver driver;
    private final String SCREENSHOTS = "./src/test/onDemandFeatureScreenshots/FeatureCreate";

    public DeleteTaskSeleniumTest(ChromeDriver driver) {
        this.driver = driver;

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        System.setProperty("sel.jup.screenshot.at.the.end.of.tests", "whenfailure");
        System.setProperty("sel.jup.screenshot.format", "png");
        System.setProperty("sel.jup.output.folder", "./src/test/onDemandFeatureScreenshots");
    }

    @Test
    public void test_Create_Feature_And_Task_Then_Delete_feature() throws InterruptedException {

        driver.get("http://localhost:8081/#/features");
        driver.manage().window().maximize();

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
        dateBox.sendKeys("12252022");
        dateBox.sendKeys(Keys.ARROW_RIGHT);
        dateBox.sendKeys("0245PM");

        WebElement submitNewFeature = driver.findElement(By.xpath("//*[contains(text(),'Create Feature')]"));
        submitNewFeature.click();

        Thread.sleep(2000);
        driver.navigate().refresh();

        Thread.sleep(2000);

        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'New Selenium Feature')]"));


        String successString = "New Selenium Feature description";

        assertThat(success.getText(), is(successString));

        Thread.sleep(2000);
        assertTrue(driver.getPageSource().contains("New Selenium Feature"));

        driver.findElement(By.xpath("//*[@id=\'editDeleteButtonsDiv\']/a")).click();
        driver.findElement(By.xpath("//*[@id=\'myBtn\']/a")).click();

        Thread.sleep(4000);

        WebElement taskName = driver.findElement((By.xpath("/html/body/div/div[5]/div/div[2]/form/fieldset/div[1]/div/input")));
        taskName.sendKeys("New Selenium task");

        WebElement taskDescription = driver.findElement(By.xpath("/html/body/div/div[5]/div/div[2]/form/fieldset/div[2]/div/textarea"));
        taskDescription.sendKeys("New Selenium task description");

        Select taskPriority = new Select(driver.findElement(By.xpath("/html/body/div/div[5]/div/div[2]/form/fieldset/div[3]/div/select")));
        taskPriority.selectByVisibleText("Medium");


        WebElement submitNewTask = driver.findElement(By.xpath("//*[contains(text(),'Create Task')]"));
        submitNewTask.click();

        driver.navigate().refresh();


        WebElement deleteTask = driver.findElement(By.xpath("/html/body/div/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[5]/a[2]"));
        deleteTask.click();

        driver.switchTo().alert().accept();
        //since the task is deleted, the document's page source should not contain the description of the task
        assertFalse(driver.getPageSource().contains("New Selenium task description"));
    }
}
