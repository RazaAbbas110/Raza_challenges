import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class Toy {
    private String name;
    private double price;

    public Toy(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Toy [name=" + name + ", price=" + price + "]";
    }
}

public class Test9 {
    public static void gotoShopPage(WebDriver driver) {
        driver.findElement(By.className("hero-unit")).findElement(By.tagName("a")).click();
    }

    public static int getNumberOfToys(WebDriver driver) {
        List<WebElement> toysTags = driver.findElement(By.className("products")).findElements(By.className("product"));

        return toysTags.size();
    }

    public static void buyNthToy(WebDriver driver, int n) {
        List<WebElement> toysTags = driver.findElement(By.className("products")).findElements(By.className("product"));
        toysTags.get(n).findElement(By.tagName("div")).findElement(By.tagName("p")).findElement(By.tagName("a"))
                .click();
    }

    public static ArrayList<Toy> buyNToys(WebDriver driver, int n) {
        int numOfToys = getNumberOfToys(driver);
        ArrayList<Toy> boughtToys = new ArrayList<>();

        List<WebElement> toysTags = driver.findElement(By.className("products")).findElements(By.className("product"));
        int randomNumber, min = 0, max = numOfToys;
        String toyName;
        double toyPrice;
        for (int i = 0; i < n; i++) {
            randomNumber = (int) (Math.random() * (max - min) + min);
            buyNthToy(driver, randomNumber);
            toyName = toysTags.get(randomNumber).findElement(By.tagName("div")).findElement(By.tagName("h4")).getText()
                    .trim();
            toyPrice = Double
                    .parseDouble(toysTags.get(randomNumber).findElement(By.tagName("div")).findElement(By.tagName("p"))
                            .findElement(By.tagName("span")).getText().trim().substring(1));
            boughtToys.add(new Toy(toyName, toyPrice));
        }

        return boughtToys;
    }

    public static void gotoCartPage(WebDriver driver) {
        driver.findElement(By.id("nav-cart")).findElement(By.tagName("a")).click();
    }

    public static int uniqueToysBought(ArrayList<Toy> boughtToys) {
        HashSet<String> uniqueToysNames = new HashSet<>();
        for (Toy toy : boughtToys) {
            uniqueToysNames.add(toy.getName());
        }
        return uniqueToysNames.size();
    }

    public static int uniqueToysInCart(WebDriver driver) {
        List<WebElement> cartItemTags = driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        return cartItemTags.size();
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://jupiter.cloud.planittesting.com/#/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        gotoShopPage(driver);

        int min = 1, max = 20;
        int numberOfToysToBuy = (int) (Math.random() * (max - min) + min);
        ArrayList<Toy> boughtToys = buyNToys(driver, numberOfToysToBuy);

        gotoCartPage(driver);

        // CHECK IF UNIQUE TOYS BOUGHT COUNT IS SAME IN CART PAGE
        if (uniqueToysBought(boughtToys) == uniqueToysInCart(driver)) {
            System.out.println("Test 9 PASSED!!!");
        } else {
            System.out.println("Test 9 FAILED...");
        }

        driver.close();
        driver.quit();
    }
}
