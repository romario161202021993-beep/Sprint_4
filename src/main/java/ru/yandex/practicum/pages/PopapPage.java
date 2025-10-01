package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class PopapPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы попап окна
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By successMessage = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");
    private final By orderNumberText = By.xpath(".//div[contains(text(), 'Номер заказа')]");

    public PopapPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }

    public void confirmOrder() {
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmBtn.click();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return successMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOrderNumberDisplayed() {
        try {
            WebElement orderNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberText));
            return orderNumber.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}