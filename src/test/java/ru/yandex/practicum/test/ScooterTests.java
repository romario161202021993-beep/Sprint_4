package ru.yandex.practicum.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.MainPage;
import ru.yandex.practicum.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ScooterTests {
    private WebDriver driver;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private String date;
    private String comment;
    private boolean isHeaderButton;

    public ScooterTests(String name, String surname, String address, String phone, String date, String comment, boolean isHeaderButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
        this.isHeaderButton = isHeaderButton;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Петров", "Москва, Ленина 1", "+79991234567", "15.05.2025", "Подъезд со двора", true},
                {"Анна", "Сидорова", "СПб, Невский 10", "+79997654321", "20.06.2025", "Звонить за 10 минут", false}
        });
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();

        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testOrderScooter() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        if (isHeaderButton) {
            mainPage.clickOrderButtonHeader();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        orderPage.fillFirstForm(name, surname, address, phone);
        orderPage.fillSecondForm(date, comment);
        orderPage.confirmOrder();

        assertTrue("Модальное окно об успешном заказе не появилось", orderPage.isSuccessModalVisible());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}