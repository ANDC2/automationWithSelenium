import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JacketsPage {
    WebDriver driver;
    public JacketsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy(String option) {
        WebElement sortDropdown = driver.findElement(By.id("sorter"));
        Select s = new Select(sortDropdown);
        s.selectByVisibleText(option);
    }

    public List<String> getItemNames() {
        List<WebElement> items = driver.findElements(By.cssSelector("strong.product.name.product-item-name > a"));
        List<String> itemNames = new ArrayList<>();

        for (WebElement item : items) {
            String itemName = item.getText();
            itemNames.add(itemName);
        }
        return itemNames;
    }

    public List<String> getItemPrices() {
        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='price']"));
        List<String> itemsPrices = new ArrayList<>();
        for (WebElement price : prices) {
            String itemPrice = price.getText();
            itemsPrices.add(itemPrice);
        }
        return itemsPrices;
    }
    public void addToCart(String productName){
        List<WebElement> productsName = driver.findElements(By.cssSelector("a.product-item-link"));
        List<WebElement> AddToCart = driver.findElements(By.xpath("/html/body/div[2]/main/div[3]/div[1]/div[4]/ol/li/div/div/div/div/div[1]/form/button/span"));
        int index=-1;
        for(int i=0;i<productsName.size();i++){
            if (productsName.get(i).getText().contains(productName)) {
                index=i;
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,500)");
                Actions actions = new Actions(driver);
                actions.moveToElement(productsName.get(index)).build().perform();
                AddToCart.get(index).submit();
                break;
            }
        }
    }

}

