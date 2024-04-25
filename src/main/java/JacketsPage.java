import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
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

    public void selectSize(String option) {
        List<WebElement> sizes = driver.findElements(By.xpath("//div[@class='swatch-option text' and text()]"));
        for (WebElement size : sizes) {
            if (size.getText().contains(option)) {
                size.click();
                break;
            }
        }
    }

    public void addToCart(String productName, String sizeOption, String colorOption) {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='column main']//li"));
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            WebElement productNameElement = products.get(i).findElement(By.cssSelector("a.product-item-link"));
            if (productNameElement.getText().equals(productName)) {
                index = i;
                WebElement productSize = products.get(i).findElement(By.xpath(".//div[@class='swatch-option text' and text()='" + sizeOption + "']"));
                WebElement productColor = products.get(i).findElement(By.xpath(".//div[@class='swatch-option color' and @option-label='" + colorOption + "']")); // Adjust the xpath for color accordingly
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", productNameElement);
                Actions actions = new Actions(driver);
                actions.moveToElement(products.get(index)).build().perform();
                productSize.click();
                productColor.click();
                List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[@title='Add to Cart']"));
                addToCartButtons.get(index).click();
                break;
            }
        }
    }

    public void addToCart(String productName) {
        List<WebElement> productsName = driver.findElements(By.cssSelector("a.product-item-link"));
        List<WebElement> AddToCart = driver.findElements(By.xpath("/html/body/div[2]/main/div[3]/div[1]/div[4]/ol/li/div/div/div/div/div[1]/form/button/span"));
        int index = -1;
        for (int i = 0; i < productsName.size(); i++) {
            if (productsName.get(i).getText().contains(productName)) {
                index = i;
                Actions actions = new Actions(driver);
                actions.moveToElement(productsName.get(index)).build().perform();
                AddToCart.get(index).submit();
                break;
            }
        }
    }

}

