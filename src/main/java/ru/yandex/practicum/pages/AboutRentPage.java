package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class AboutRentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы формы аренды
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private final By rentalPeriodOption = By.xpath(".//div[text()='трое суток']");
    private final By blackCheckbox = By.id("black");
    private final By greyCheckbox = By.id("grey");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[text()='Заказать' and contains(@class, 'Button_Middle')]");
    private final By pageHeader = By.className("Order_Header__BZXOb");

    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }

    public void setDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        dateInput.sendKeys(date);
        // Кликаем на заголовок страницы чтобы закрыть календарь
        driver.findElement(pageHeader).click();
    }

    public void setRentalPeriod() {
        driver.findElement(rentalPeriodDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption)).click();
    }

    public void selectScooterColor(boolean isBlack) {
        if (isBlack) {
            driver.findElement(blackCheckbox).click();
        } else {
            driver.findElement(greyCheckbox).click();
        }
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        WebElement orderBtn = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        orderBtn.click();
    }

    public void fillRentForm(String date, boolean isBlackColor, String comment) {
        setDate(date);
        setRentalPeriod();
        selectScooterColor(isBlackColor);
        setComment(comment);
    }
}