import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 11.09.2018<br>
 * Time: 23:15<br>
 * todo javadoc
 */
public class InsuranceSberbank {

    WebDriver driver;
    String BaseURL;

    @Before
    public void beforeSberTest(){
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        BaseURL = "http://www.sberbank.ru/ru/person";

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(BaseURL);
    }

    @Test
    public void insuranceSberTest() {
        //Нажать на "Страхование"
        driver.findElement(By.xpath("//span[contains(text(), \"Страхование\")]")).click();

        //Выбрать "Путешествия и покупки"
        driver.findElement(By.xpath("//li [@class = \"lg-menu__sub-item\"]/*[contains(text(), \"Путешествия и покупки\")]")).click();

        //Проверить наличие на странице заголовка "Страхование путешественников"
        WebElement title = driver.findElement(By.xpath("//h3[contains(text(), \"Страхование путешественников\")]"));
        Assert.assertEquals("Страхование путешественников", title.getText());

        //Нажать на "Оформить Онлайн"

        driver.findElement(By.xpath("(//a[contains(text(), \"Оформить онлайн\")])[1]")).click();

        //Переключение на новую вкладку

        ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        // На вкладке "Выбор полиса"  выбрать сумму страховой защиты – Минимальная

        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        //driver.findElement(By.xpath("//div[text() = \"Минимальная\"]/..")).click();

        //Нажать "Оформить"

        driver.findElement(By.xpath("//span[text() = \"Оформить\"]")).click();






    }

    @After

    public void afterSberTest(){

    }
}
