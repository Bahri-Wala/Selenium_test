import java.io.IOException;
import java.time.Duration;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TunisianetTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));


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
        Thread.sleep(500);
        WebElement passwordField = driver.findElement(By.cssSelector(".form-group > div > div > input"));
        passwordField.sendKeys("testtest");

        //Submit form
        Thread.sleep(1000);
        WebElement submitButton = driver.findElement(By.id("submit-login"));
        submitButton.click();

        // Search a product
        Thread.sleep(1000);
        WebElement searchBar = driver.findElement(By.className("search_query"));
        searchBar.sendKeys("Smartphone");
        Thread.sleep(1000);
        WebElement searchButton = driver.findElement(By.cssSelector("#sp-btn-search > button"));
        searchButton.click();

        // Choose product
        WebElement product = driver.findElements(By.className("item-product")).get(0);
        product.click();

        // Add product to cart
        WebElement addToCartButton = driver.findElement(By.className("add-to-cart"));
        addToCartButton.click();
        Thread.sleep(500);
        WebElement continueButton = driver.findElement(By.cssSelector("a.btn-block"));
        continueButton.click();

        // Order
        Thread.sleep(1000);
        WebElement orderButton = driver.findElement(By.cssSelector(".checkout > div > a"));
        orderButton.click();

        // fill address form
        Thread.sleep(500);
        List<WebElement> fields = driver.findElements(By.cssSelector("input:required"));
        for (WebElement field : fields) {
            Thread.sleep(500);
            if (field.getAttribute("value").isEmpty()) {
                if (field.getAttribute("type").equals("tel")) {
                    field.sendKeys("12345678");
                } else field.sendKeys("test");
            }
        }
        Thread.sleep(500);
        submitButton = driver.findElement(By.cssSelector("footer > button"));
        submitButton.click();

        //Choose delivery mode
        Thread.sleep(2000);
        submitButton = driver.findElement(By.cssSelector("button[name=\"confirmDeliveryOption\"]"));
        System.out.println(submitButton.getAttribute("name"));
        submitButton.click();

        //Choose payment option
        Thread.sleep(1000);
        WebElement paymentOption = driver.findElement(By.id("payment-option-1"));
        paymentOption.click();
        Thread.sleep(500);
        WebElement conditionAgreementCheckbox = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        conditionAgreementCheckbox.click();


        //Submit order
        Thread.sleep(500);
        submitButton = driver.findElement(By.cssSelector("#payment-confirmation > div > button"));
        System.out.println(submitButton.getAttribute("name"));
        submitButton.click();

        //Quit
        Thread.sleep(2000);
        driver.quit();

    }
}
