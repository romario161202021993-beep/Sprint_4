package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By cookieButton = By.id("rcc-confirm-button");
    private final By orderButtonHeader = By.xpath("//button[@class='Button_Button__ra12g' and text()='Заказать']");
    private final By orderButtonBottom = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acceptCookies() {
        WebElement cookieBtn = driver.findElement(cookieButton);
        if (cookieBtn.isDisplayed()) {
            cookieBtn.click();
        }
    }

    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }
}