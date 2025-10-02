package ru.yandex.practicum.test;

import org.junit.Rule;
import org.junit.Test;
import ru.yandex.practicum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ScooterTest {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    public void testNonExistingOrderShowsError() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        mainPage.open();
        mainPage.acceptCookies();

        // Проверяем несуществующий заказ
        mainPage.checkOrderStatus("999999");

        boolean isErrorDisplayed = mainPage.isNotFoundImageDisplayed();
        assertTrue("Сообщение об ошибке для несуществующего заказа не отображается", isErrorDisplayed);
    }
}