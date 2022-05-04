import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test3 {
    private static String[] toyNames;
    static {
        toyNames = new String[] {
                "Teddy Bear",
                "Stuffed Frog",
                "Handmade Doll",
                "Fluffy Bunny",
                "Smiley Bear",
                "Funny Cow",
                "Valentine Bear",
                "Smiley Face",
        };
    }

    public static void gotoShopPage(WebDriver driver) {
        driver.findElement(By.className("hero-unit")).findElement(By.tagName("a")).click();
    }

    public static int getNumberOfToys(WebDriver driver) {
        List<WebElement> toysTags = driver.findElement(By.className("products")).findElements(By.className("product"));

        return toysTags.size();
    }

    public static String getNthToyName(WebDriver driver, int n) {
        List<WebElement> toysTags = driver.findElements(By.className("product"));

        WebElement toyTag = toysTags.get(n);
        String toyName = toyTag.findElement(By.tagName("div")).findElement(By.tagName("h4")).getText().trim();

        return toyName;
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

        String toyName = getNthToyName(driver, n);

        // CHECK IF RANDOM TOY INDEX IS CORRECT
        if (toyName.equals(toyNames[n])) {
            System.out.println("Test 3 PASSED!!!");
        } else {
            System.out.println("Test 3 FAILED...");
        }

        driver.close();
        driver.quit();
    }
}
