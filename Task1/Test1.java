import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {
    public static int cartCount(WebDriver driver) {
        WebElement cartCountElement = driver.findElement(By.id("nav-cart")).findElement(By.tagName("a"))
                .findElement(By.tagName("span"));
        int cartCount = Integer.parseInt(cartCountElement.getText().trim());
        return cartCount;
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");


        WebDriver driver = new ChromeDriver();
        driver.get("https://jupiter.cloud.planittesting.com/#/");
        driver.manage().window().maximize();

        int cartCount = cartCount(driver);

        // CHECK INITIAL CART COUNT IS 0
        if (cartCount == 0) {
            System.out.println("Test 1 PASSED!!!");
        } else {
            System.out.println("Test 1 FAILED...");
        }

        driver.close();
        driver.quit();
    }
}