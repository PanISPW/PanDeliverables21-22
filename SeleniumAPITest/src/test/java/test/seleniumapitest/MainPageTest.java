package test.seleniumapitest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://github.com/PanISPW/PanSoftware");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkGoalsIssueIsClosed() {

        driver.findElement(By.xpath("//*[@id=\"issues-tab\"]/span[1]")).click();

        driver.findElement(By.xpath("//*[@id=\"js-issues-search\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"js-issues-search\"]")).sendKeys("is:issue is:closed");
        driver.findElement(By.xpath("//*[@id=\"js-issues-search\"]")).sendKeys(Keys.RETURN);

        String output = driver.findElement(By.xpath("/html/body/div[4]/div/main/div[2]/div/div/div[4]/div[2]/div/div[7]/div/div[2]/a")).getText();

        assertEquals("Goals", output);
    }
}
