package seleniumFeatures;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SeleniumExtension.class)
public class GetFeatureAndAddTaskTest {
    ChromeDriver driver;

    public GetFeatureAndAddTaskTest(ChromeDriver driver) {
        this.driver = driver;
    }


    void addFeature() throws InterruptedException {

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
        dateBox.sendKeys("11262023");
        dateBox.sendKeys(Keys.ARROW_RIGHT);
        dateBox.sendKeys("0345PM");

        WebElement submitNewFeature = driver.findElement(By.xpath("//*[contains(text(),'Create Feature')]"));
        submitNewFeature.click();

        Thread.sleep(2000);
        driver.navigate().refresh();

        Thread.sleep(2000);

        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'New Selenium Feature')]"));
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        String successString = "New Selenium Feature description";

        assertThat(success.getText(), is(successString));

    }

    @Test
    void Get_Feature_Details() throws InterruptedException{
        addFeature();
        driver.findElement(By.linkText("New Selenium Feature")).click();
        Thread.sleep(2000);
        WebElement success = driver.findElement(By.xpath("//*[contains(text(),'New Selenium Feature')]"));
        String successString = "New Selenium Feature";
        assertThat(success.getText(), is(successString));
    }

    @Test
    void Add_Task_Test() throws InterruptedException{
        Get_Feature_Details();
        driver.findElement(By.xpath("//*[@id=\"myBtn\"]/a")).click();
        Thread.sleep(2000);

        WebElement taskName = driver.findElement((By.xpath("/html/body/div/div[5]/div/div[2]/form/fieldset/div[1]/div/input")));
        taskName.sendKeys("New Selenium task");

        WebElement taskDescription = driver.findElement(By.xpath("/html/body/div/div[5]/div/div[2]/form/fieldset/div[2]/div/textarea"));
        taskDescription.sendKeys("New Selenium task description");

        Select taskPriority = new Select(driver.findElement(By.xpath("/html/body/div/div[5]/div/div[2]/form/fieldset/div[3]/div/select")));
        taskPriority.selectByVisibleText("Medium");


        WebElement submitNewTask = driver.findElement(By.xpath("//*[contains(text(),'Create Task')]"));
        submitNewTask.click();
    }

}
