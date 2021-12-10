import java.io.IOException;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TunisianetTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));


        driver.get("https://www.tunisianet.com.tn/");

        // click on the user icon
        Thread.sleep(1000);
        WebElement userIcon = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        userIcon.click();

        // Click on Connexion button
        Thread.sleep(1000);
        WebElement connexionButton = driver.findElement(By.cssSelector(".user-down > li > a > span"));
        connexionButton.click();

        // Fill the signin form
        Thread.sleep(1000);
        WebElement emailField = driver.findElement(By.cssSelector(".form-group > div > input"));
        emailField.sendKeys("seliniumtest@gmail.com");
        Thread.sleep(1000);
        WebElement passwordField = driver.findElement(By.cssSelector(".form-group > div > div > input"));
        passwordField.sendKeys("testtest");

        //Submit form
        Thread.sleep(1000);
        WebElement submitButton = driver.findElement(By.id("submit-login"));
        submitButton.click();

        // Search a product
        Thread.sleep(1000);
        WebElement searchBar = driver.findElement(By.className("search_query"));
        searchBar.sendKeys("Smartphone Huawei Nova 7i");
        Thread.sleep(1000);
        WebElement searchButton = driver.findElement(By.cssSelector("#sp-btn-search > button"));
        searchButton.click();

        // Choose product
        Thread.sleep(1000);
        WebElement product = driver.findElement(By.id("product-description-short-41240"));
        product.click();

        // Add product to cart
        Thread.sleep(1000);
        WebElement addToCartButton = driver.findElement(By.className("add-to-cart"));
        addToCartButton.click();
        Thread.sleep(1000);
        WebElement continueButton = driver.findElement(By.cssSelector("a.btn-block"));
        continueButton.click();

        //Order
        Thread.sleep(1000);
        WebElement orderButton = driver.findElement(By.cssSelector(".checkout > div > a"));
        orderButton.click();

        //Quit
        Thread.sleep(2000);
        driver.quit();

    }
}
