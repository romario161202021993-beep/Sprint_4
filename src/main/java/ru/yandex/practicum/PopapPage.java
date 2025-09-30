package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PopapPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By confirmBtn = By.xpath("//button[text()='Да']");
    private final By successText = By.xpath("//div[@class='Order_Text__2broi' and text()='Номер заказа: ']");

    public PopapPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void confirm() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(confirmBtn));
        btn.click();
    }

    public void waitForSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successText));
    }
}