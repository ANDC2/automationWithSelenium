
import com.google.common.collect.Ordering;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class WebPageTest {
    WebDriver driver;
    MainPage mainPage;
    JacketsPage jacketsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        mainPage = new MainPage(driver);
        jacketsPage = new JacketsPage(driver);
    }

//    @AfterEach
//    public void close() {
//        driver.quit();
//    }

    @Test
    public void testNavigateToMainPage() {
        mainPage.navigateToMainPage();
        assertThat(driver.getTitle(), is("Home Page"));
    }

    @Test
    public void testLogIn() {
        mainPage.navigateToMainPage();
        mainPage.acceptCookies();
        mainPage.clickOnSignInButton();
        mainPage.logIn();
        assertThat(driver.findElement(By.xpath("//span[@class='logged-in']")).getText(), is("Welcome, Test Test!"));
    }

    @Test
    public void testLandingOnJacketsSection() {
        mainPage.navigateToMainPage();
        mainPage.acceptCookies();
        mainPage.clickOnSignInButton();
        mainPage.logIn();
        mainPage.clickOnJacketsSection();
        assertThat(driver.getTitle(), is("Jackets - Tops - Women"));
    }

    @Test
    public void testSortByName_IsSortingByName() {
        mainPage.navigateToMainPage();
        mainPage.acceptCookies();
        mainPage.clickOnSignInButton();
        mainPage.logIn();
        mainPage.clickOnJacketsSection();
        jacketsPage.sortBy("Product Name");
        assertTrue(Ordering.natural().isOrdered(jacketsPage.getItemNames()));

    }

    @Test
    public void testSortByPrice_IsSortingByPrice() {
        mainPage.navigateToMainPage();
        mainPage.acceptCookies();
        mainPage.clickOnSignInButton();
        mainPage.logIn();
        mainPage.clickOnJacketsSection();
        jacketsPage.sortBy("Price");
        List<String> sortedPrices = jacketsPage.getItemPrices();
        for (int i = 0; i < 11; i++) {
            double currentPrice = Double.parseDouble(sortedPrices.get(i).replaceAll("[^\\d.]", ""));
            double nextPrice = Double.parseDouble(sortedPrices.get(i + 1).replaceAll("[^\\d.]", ""));
            assertTrue(currentPrice <= nextPrice, " Prices are not sorted correctly");
        }

    }


    @Test
    public void addToCart() {
        mainPage.navigateToMainPage();
        mainPage.acceptCookies();
        mainPage.clickOnSignInButton();
        mainPage.logIn();
        mainPage.clickOnJacketsSection();
        jacketsPage.getItemNames();
        jacketsPage.addToCart("Neve Studio Dance Jacket");
    }
}





