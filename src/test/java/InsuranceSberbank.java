import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

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
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[text() = \"Минимальная\"]/.."))));

        // На вкладке "Выбор полиса"  выбрать сумму страховой защиты – Минимальная

        driver.findElement(By.xpath("//div[text() = \"Минимальная\"]/..")).click();

        //Нажать "Оформить"

        driver.findElement(By.xpath("//span[text() = \"Оформить\"]")).click();

        // На вкладке "Оформить" заполнить поля
        driver.findElement(By.xpath("(//input[contains(@name, \"surname\")])[1]")).sendKeys("Ivanov");
        driver.findElement(By.xpath("//input[contains(@name, \"insured0_name\")]")).sendKeys("Petr");
        driver.findElement(By.xpath("//input[contains(@name, \"insured0_birthDate\")]")).sendKeys("21.12.1990");
        driver.findElement(By.xpath("(//input[contains(@name, \"surname\")])[2]")).sendKeys("Петров");
        driver.findElement(By.xpath("//input[@name = \"name\"]")).sendKeys("Иван");
        driver.findElement(By.xpath("//input[@name = \"middlename\"]")).sendKeys("Иванович");
        driver.findElement(By.xpath("//input[@name = \"birthDate\"]")).sendKeys("21.12.1990");
        driver.findElement(By.xpath("//input[@placeholder = \"Серия\"]")).sendKeys("6911");
        driver.findElement(By.xpath("//input[@placeholder = \"Номер\"]")).sendKeys("391556");
        driver.findElement(By.xpath("//input[@name = \"issueDate\"]")).sendKeys("20.01.2011");
        driver.findElement(By.xpath("//textarea[@name = \"issuePlace\"]")).sendKeys("УФМС России");

        Assert.assertEquals("Ivanov", driver.findElement(By.xpath("(//input[contains(@name, \"surname\")])[1]")).getAttribute("value"));
        Assert.assertEquals("Petr", driver.findElement(By.xpath("//input[contains(@name, \"insured0_name\")]")).getAttribute("value"));
        Assert.assertEquals("21.12.1990", driver.findElement(By.xpath("//input[contains(@name, \"insured0_birthDate\")]")).getAttribute("value"));
        Assert.assertEquals("Петров", driver.findElement(By.xpath("(//input[contains(@name, \"surname\")])[2]")).getAttribute("value"));
        Assert.assertEquals("Иван", driver.findElement(By.xpath("//input[@name = \"name\"]")).getAttribute("value"));
        Assert.assertEquals("Иванович", driver.findElement(By.xpath("//input[@name = \"middlename\"]")).getAttribute("value"));
        Assert.assertEquals("21.12.1990", driver.findElement(By.xpath("//input[@name = \"birthDate\"]")).getAttribute("value"));
        Assert.assertEquals("6911", driver.findElement(By.xpath("//input[@placeholder = \"Серия\"]")).getAttribute("value"));
        Assert.assertEquals("20.01.2011", driver.findElement(By.xpath("//input[@name = \"issueDate\"]")).getAttribute("value"));
        Assert.assertEquals("УФМС России", driver.findElement(By.xpath("//textarea[@name = \"issuePlace\"]")).getAttribute("value"));

        //Нажать "Продолжить"
        driver.findElement(By.xpath("//span[text() = \"Продолжить\"]")).click();
        Assert.assertEquals("Заполнены не все обязательные поля", driver.findElement(By.xpath("//div[contains(text(), \"Заполнены не все обязательные поля\")]")).getText());

    }

    @After

    public void afterSberTest(){

    }
}
