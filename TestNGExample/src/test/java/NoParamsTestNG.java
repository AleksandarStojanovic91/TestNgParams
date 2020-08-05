import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NoParamsTestNG {

    public static WebDriver driver;
    String URL = "http://www.google.com";
    String search = "Selenium";
    String search2 = "TestNG";

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(URL);
    }
    @Test
    public void Search(){
        WebElement searchField = driver.findElement(By.cssSelector("[name=\"q\"]"));
        searchField.sendKeys(search);
        WebElement searchButton = driver.findElement(By.cssSelector("[name=\"btnK\"]"));
        searchButton.click();
    }
    @Test
    public void Search2(){
        WebElement searchField = driver.findElement(By.cssSelector("[name=\"q\"]"));
        searchField.sendKeys(search2);
        WebElement searchButton = driver.findElement(By.cssSelector("[name=\"btnK\"]"));
        searchButton.click();
    }
    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}