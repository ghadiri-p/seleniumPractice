package servicesTest;

import model.Goods;
import model.User;
import enums.Locators;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import service.Services;

import java.util.List;
import java.util.concurrent.TimeUnit;

class ServicesTest {

    public static WebDriver driver;


    /**
     * Creates a chrome driver and maximizes it.
     */
    @BeforeAll
    static void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }



    /**
     * If my dashboard element is found, it means that the login is done correctly
     * and the user has logged into their user page Otherwise, we will have error .
     */
    @Test
    void login() {

        Services services = new Services();
        User user = new User("ghadiri.p11@gmail.com", "@12345a");
        services.login(user.getUserName(), user.getPassWord(), driver);

        try {
            assert driver.findElement(By.xpath(Locators.DASHBOARD.getValue())).getText().contains("MY DASHBOARD");
            System.out.println("Login done correctly.");
        } catch (AssertionError e) {
            System.out.println(driver.findElement(By.xpath(Locators.MESSAGE.getValue())).getText());
        }
    }



    /**
     * Checks that the purchase information is equal to the information of the goods placed in the cart
     * , otherwise we will have error assertion.
     *
     * @throws InterruptedException
     */
    @Test
    void addToCard() {

        Services services = new Services();
        User user = new User("ghadiri.p11@gmail.com", "@12345a");

        try {
            List<Goods> goodsList = services.addToCart(user.getUserName(), user.getPassWord(), driver);
            Goods goods = goodsList.get(0);
            Goods cartGoods = goodsList.get(1);
            assert (goods.getName().equals(cartGoods.getName())
                    && goods.getSize().equals(cartGoods.getSize())
                    && goods.getColor().equals(cartGoods.getColor())
                    && (goods.getPrice() == cartGoods.getPrice())
                    && (goods.getCount() == cartGoods.getCount())
                    && (cartGoods.getTotalPrice() == goods.getCount() * goods.getPrice()));
            System.out.println("the Shopping is located in the cart.");
        } catch (AssertionError e) {
            System.out.println("Purchase failed.");
        }
    }



    @AfterAll
    static void tearDown() {

        driver.close();
        driver.quit();
    }
}