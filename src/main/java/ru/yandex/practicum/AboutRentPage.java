package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AboutRentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By date = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By period = By.xpath("//div[@class='Dropdown-control']");
    private final By periodOption = By.xpath("//div[text()='трое суток']");
    private final By color = By.id("black");
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderBtn = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillForm(String date, String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.date));
        driver.findElement(this.date).sendKeys(date);
        driver.findElement(By.className("Order_Header__BZXOb")).click();
        driver.findElement(period).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(periodOption));
        driver.findElement(periodOption).click();
        driver.findElement(color).click();
        driver.findElement(this.comment).sendKeys(comment);
    }

    public void clickOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderBtn));
        driver.findElement(orderBtn).click();
    }
}