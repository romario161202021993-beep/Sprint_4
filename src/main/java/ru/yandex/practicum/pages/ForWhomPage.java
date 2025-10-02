package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class ForWhomPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы формы "Для кого самокат"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.className("select-search__input");
    private final By metroOption = By.xpath(".//div[@class='select-search__select']//button[1]");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    public ForWhomPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setMetroStation() {
        driver.findElement(metroField).click();
        wait.until(ExpectedConditions.elementToBeClickable(metroOption)).click();
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextBtn.click();
    }

    public void fillCustomerForm(String name, String surname, String address, String phone) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStation();
        setPhone(phone);
    }
}