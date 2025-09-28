package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By metroStationOption = By.xpath("//div[@class='select-search__select']//li[1]");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.xpath("//div[@class='Dropdown-control']");
    private final By rentalPeriodOption = By.xpath("//div[text()='трое суток']");
    private final By blackCheckbox = By.id("black");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    private final By confirmOrderButton = By.xpath("//button[text()='Да']");
    private final By successModal = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void fillFirstForm(String name, String surname, String address, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroStationOption));
        driver.findElement(metroStationOption).click();
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    public void fillSecondForm(String date, String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
        driver.findElement(dateField).sendKeys(date);
        // Закрываем календарь
        driver.findElement(By.className("Order_Header__BZXOb")).click();
        driver.findElement(rentalPeriodField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodOption));
        driver.findElement(rentalPeriodOption).click();
        driver.findElement(blackCheckbox).click();
        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        driver.findElement(confirmOrderButton).click();
    }

    public boolean isSuccessModalVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successModal));
        return driver.findElement(successModal).isDisplayed();
    }
}