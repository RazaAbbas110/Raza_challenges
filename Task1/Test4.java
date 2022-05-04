import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test4 {
    private static String[] toyPrices;
    static {
        toyPrices = new String[] {
                "$12.99",
                "$10.99",
                "$10.99",
                "$9.99",
                "$14.99",
                "$10.99",
                "$14.99",
                "$9.99",
        };
    }

    public static void gotoShopPage(WebDriver driver) {
        driver.findElement(By.className("hero-unit")).findElement(By.tagName("a")).click();
    }

    public static int getNumberOfToys(WebDriver driver) {
        List<WebElement> toysTags = driver.findElement(By.className("products")).findElements(By.className("product"));

        return toysTags.size();
    }

    public static String getNthToyPrice(WebDriver driver, int n) {
        List<WebElement> toysTags = driver.findElements(By.className("product"));

        WebElement toyTag = toysTags.get(n);
        String toyPrice = toyTag.findElement(By.tagName("div")).findElement(By.tagName("p"))
                .findElement(By.tagName("span")).getText().trim();

        return toyPrice;
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://jupiter.cloud.planittesting.com/#/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        gotoShopPage(driver);

        int min = 0, max = getNumberOfToys(driver);
        int n = (int) (Math.random() * (max - min) + min);

        String toyPrice = getNthToyPrice(driver, n);

        // CHECK IF RANDOM TOY INDEX IS CORRECT
        if (toyPrice.equals(toyPrices[n])) {
            System.out.println("Test 4 PASSED!!!");
        } else {
            System.out.println("Test 4 FAILED...");
        }

        driver.close();
        driver.quit();
    }
}
