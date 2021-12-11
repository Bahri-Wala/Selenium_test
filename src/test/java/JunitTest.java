import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JunitTest {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeAll
    public static void initialize() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void prepareDriver(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().setScriptTimeout(Duration.ofMinutes(2));

        js = (JavascriptExecutor) driver;
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Backbone.js",
            "Vue.js",
            "React"})
    public void testCases(String technology) throws InterruptedException{

        // Connect to website
        driver.get("https://todomvc.com");

        // Click on technology link
        driver.findElement(By.linkText(technology)).click();

        // add 3 Todos
        Thread.sleep(1000);
        add("Buy meat");
        Thread.sleep(1000);
        add("Meet a friend");
        Thread.sleep(1000);
        add("Clean the car");

        //Chose 2 elements from the list
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("li:nth-child(2) .toggle")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("li:nth-child(3) .toggle")).click();

        //Compare results
        assertResult(1);
    }

    private void add(String todo) {
        WebElement element = driver.findElement(By.className("new-todo"));
        element.sendKeys(todo);
        element.sendKeys(Keys.RETURN);
    }

    private void assertResult(int expectedResult) {
        WebElement element = driver.findElement(By.cssSelector("footer > span "));
        String expectedText = expectedResult+ " item left";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}