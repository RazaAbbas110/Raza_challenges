import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test2 {
    public static void gotoShopPage(WebDriver driver) {
        driver.findElement(By.className("hero-unit")).findElement(By.tagName("a")).click();
    }

    public static int getNumberOfToys(WebDriver driver) {
        List<WebElement> toysTags = driver.findElement(By.className("products")).findElements(By.className("product"));

        return toysTags.size();
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://jupiter.cloud.planittesting.com/#/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        gotoShopPage(driver);
        int numOfToys = getNumberOfToys(driver);

        // CHECK NUMBER OF AVAILABLE TOYS
        if (numOfToys == 8) {
            System.out.println("Test 2 PASSED!!!");
        } else {
            System.out.println("Test 2 FAILED...");
        }

        driver.close();
        driver.quit();
    }
}
