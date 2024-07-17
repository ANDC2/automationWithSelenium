
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver=driver;
    }

    public void navigateToMainPage() {
        driver.get("https://magento.softwaretestingboard.com/");
    }
    public  void acceptCookies(){
        driver.findElement(By.xpath("//p[@class='fc-button-label' and text()='Consent']")).click();

    }
    public void clickOnSignInButton(){
        driver.findElement(By.className("authorization-link")).click();
    }
    public void logIn(){
        driver.findElement(By.id("email")).sendKeys("test@test.selenium.com");
        driver.findElement(By.id("pass")).sendKeys("test@test1");
        driver.findElement(By.id("send2")).click();
    }

    public void clickOnJacketsSection(){
        Actions a = new Actions(driver);
        WebElement move = driver.findElement(By.id("ui-id-4"));
        a.moveToElement(move).click().build().perform();
        driver.findElement(By.linkText("Jackets")).click();
    }

}

