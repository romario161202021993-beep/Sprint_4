package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AboutRentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.xpath("//div[@class='Dropdown-control']");
    private final By periodOption = By.xpath("//div[text()='трое суток']");
    private final By colorBlack = By.id("black");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderBtn = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    private final By header = By.className("Order_Header__BZXOb");

    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void fillForm(String date, String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateField)).sendKeys(date);
        driver.findElement(header).click();
        driver.findElement(rentalPeriod).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(periodOption)).click();
        driver.findElement(colorBlack).click();
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(orderBtn));
        btn.click();
    }
}