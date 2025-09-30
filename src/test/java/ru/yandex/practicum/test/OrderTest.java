package ru.yandex.practicum.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.*;

@RunWith(Parameterized.class)
public class OrderTest {

    private final boolean useTop;
    private final String name, surname, address, phone, date, comment;

    public OrderTest(boolean useTop, String name, String surname, String address, String phone, String date, String comment) {
        this.useTop = useTop;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Заказ через {0} кнопку")
    public static Object[][] data() {
        return new Object[][]{
                {true, "Иван", "Иванов", "Москва, Ленина 1", "+79991112233", "15.06.2025", "Позвоните заранее"},
                {false, "Анна", "Петрова", "СПб, Невский 10", "+79876543210", "20.06.2025", "Оставить у двери"}
        };
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testOrder() {
        WebDriver driver = factory.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.acceptCookies();
        main.clickOrder(useTop);

        ForWhomPage forWhom = new ForWhomPage(driver);
        forWhom.fillForm(name, surname, address, phone);
        forWhom.clickNext();

        AboutRentPage rent = new AboutRentPage(driver);
        rent.fillForm(date, comment);
        rent.clickOrderButton();

        PopapPage popap = new PopapPage(driver);
        popap.confirm();
        popap.waitForSuccessMessage();
    }
}