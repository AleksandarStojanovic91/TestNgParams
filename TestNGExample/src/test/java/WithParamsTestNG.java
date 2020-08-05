import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.security.auth.Subject;
import java.util.concurrent.TimeUnit;

public class WithParamsTestNG {
    public static WebDriver driver;
    String URLGoogle = "http://www.google.com";
    String URLAutomationPractice = "http://automationpractice.com/index.php";

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

//    @Test(enabled = false)
//    @Parameters({"author","book"})
//    public void Search(String author, String book){
//        driver.get(URLGoogle);
//        WebDriverWait wait = new WebDriverWait(driver,30);
//        WebElement searchField = driver.findElement(By.cssSelector("[name=\"q\"]"));
//        searchField.sendKeys(author +" "+book);
//        WebElement searchButton = driver.findElement(By.cssSelector("div.FPdoLc.tfB0Bf [name=\"btnK\"]"));
//        driver.findElement(By.cssSelector("#hplogo")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
//        searchButton.click();
//    }

    @Test
    @Parameters
    ({"email", "orderReference", "message", "subjectHeaderValue", "errorMessage", "errorBoxSelector", "boxColor", "textSelector"})
    public void contactUsAutomationPractice(String email, String orderReference, String message, String subjectHeaderValue, String errorMessage, String errorBoxSelector, String boxColor, String textSelector) {
        driver.get(URLAutomationPractice);
        driver.findElement(By.cssSelector("#contact-link>a")).click();

        Select subject = new Select(driver.findElement(By.cssSelector("#id_contact")));
        subject.selectByValue(subjectHeaderValue);

        driver.findElement(By.cssSelector("#email")).sendKeys(email);
        driver.findElement(By.cssSelector("#id_order")).sendKeys(orderReference);
        driver.findElement(By.cssSelector("#message")).sendKeys(message);

        driver.findElement(By.cssSelector("#submitMessage")).click();


        WebElement alert = driver.findElement(By.cssSelector(errorBoxSelector));

        Assert.assertTrue(alert.isDisplayed());
        Assert.assertEquals(alert.getCssValue("background-color"),boxColor);

        WebElement errorText = driver.findElement(By.cssSelector(textSelector));

        WebDriverWait wait = new WebDriverWait(driver,30);

        wait.until(ExpectedConditions.textToBePresentInElement(errorText,errorMessage));
        Assert.assertEquals(errorText.getText(),errorMessage);
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
