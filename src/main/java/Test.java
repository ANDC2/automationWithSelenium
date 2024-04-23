
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // login
        driver.findElement(By.xpath("button[aria-label='Consent'] p[class='fc-button-label']")).click();
        driver.findElement(By.className("authorization-link")).click();
        driver.findElement(By.id("email")).sendKeys("TestSelenium@mail.ro");
        driver.findElement(By.id("pass")).sendKeys("TestSelenium1");
        driver.findElement(By.id("send2")).click();
        Actions a = new Actions(driver);
        WebElement move = driver.findElement(By.id("ui-id-4"));
        a.moveToElement(move).click().build().perform();
        driver.findElement(By.linkText("Jackets")).click();
        // sort by
        WebElement sorter = driver.findElement(By.id("sorter"));
        Select s = new Select(sorter);
        s.selectByIndex(2);
        // add article to cart
        driver.findElement(By.linkText("Adrienne Trek Jacket")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        driver.findElement(By.xpath("//div[@class='swatch-option text'][1]")).click();
        driver.findElement(By.xpath("//div[@class='swatch-option color'][3]")).click();
        WebElement quantity = driver.findElement(By.xpath("//input[@type='number']"));
        quantity.clear();
        quantity.sendKeys("4");
        driver.findElement(By.id("product-addtocart-button")).click();

        // select option from autocomplete
        driver.findElement(By.id("search")).sendKeys("wat");
        List<WebElement> options = driver.findElements(By.xpath("//li[contains(@id ,'qs-option')]"));
        boolean optionFound = false;
        for (WebElement option : options)

        {
            if (option.getText().contains("watch")) {
                optionFound = true;
                option.click();
                break;
            }
        }
        if (!optionFound) {

            System.out.println("No matching items");
        }
        // sort by price
        WebElement sorter2 = driver.findElement(By.id("sorter"));
        Select dropdown = new Select(sorter2);
        dropdown.selectByVisibleText("Price");
        // select items by index, from list and add it to cart
        List<WebElement> productsName = driver.findElements(By.cssSelector("a.product-item-link"));
        List<WebElement> AddToCart = driver.findElements(By.xpath(
                "//html/body/div[2]/main/div[3]/div[1]/div[2]/div[2]/ol/li/div/div/div[3]/div/div[1]/form/button"));
        int[] index = { 3, 5, 8 };
        int x, y;
        for (x = 0; x < productsName.size(); x++) {
            for (y = 0; y < index.length; y++) {
                if (x == index[y]) {
                    js.executeScript("window.scrollBy(0,500)");
                    a.moveToElement(productsName.get(x)).build().perform();
                    AddToCart.get(x).submit();
                    break;

                }
            }
        }
        // go to cart to finish with checkout
        driver.findElement(By.xpath("//div[2]/header/div[2]/div[1]/a")).click();
        driver.findElement(By.id("top-cart-btn-checkout")).click();
        driver.findElement(By.cssSelector("input[value='tablerate_bestway']")).click();
        driver.findElement(By.xpath("//button[@class='button action continue primary']")).click();
        driver.findElement(By.cssSelector("button[title='Place Order']")).click();
    }
}

