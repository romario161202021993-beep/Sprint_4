package ru.yandex.practicum.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.pages.AboutRentPage;
import ru.yandex.practicum.pages.ForWhomPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.PopapPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private final boolean useTopButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;
    private final boolean isBlackColor;
    private final String comment;

    public OrderTest(boolean useTopButton, String name, String surname, String address,
                     String phone, String date, boolean isBlackColor, String comment) {
        this.useTopButton = useTopButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.isBlackColor = isBlackColor;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Заказ через {0} кнопку: {1} {2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {true, "Иван", "Иванов", "Москва, ул. Ленина, 1", "+79991112233", "15.06.2024", true, "Позвоните за час"},
                {false, "Анна", "Петрова", "Санкт-Петербург, Невский пр., 10", "+79876543210", "20.06.2024", false, "Оставить у двери"}
        };
    }

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    public void testSuccessfulOrder() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        mainPage.open();
        mainPage.acceptCookies();

        // Нажимаем на кнопку заказа
        mainPage.clickOrderButton(useTopButton);

        // Заполняем форму "Для кого самокат"
        ForWhomPage forWhomPage = new ForWhomPage(driverFactory.getDriver());
        forWhomPage.fillCustomerForm(name, surname, address, phone);
        forWhomPage.clickNextButton();

        // Заполняем форму "Про аренду"
        AboutRentPage aboutRentPage = new AboutRentPage(driverFactory.getDriver());
        aboutRentPage.fillRentForm(date, isBlackColor, comment);
        aboutRentPage.clickOrderButton();

        // Подтверждаем заказ в попапе
        PopapPage popapPage = new PopapPage(driverFactory.getDriver());
        popapPage.confirmOrder();

        // Проверяем успешное оформление заказа
        boolean isSuccess = popapPage.isSuccessMessageDisplayed();
        assertTrue("Заказ не был успешно оформлен", isSuccess);
    }
}