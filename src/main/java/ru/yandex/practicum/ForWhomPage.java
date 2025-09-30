package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForWhomPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By name = By.xpath("//input[@placeholder='* Имя']");
    private final By surname = By.xpath("//input[@placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath("//input[@placeholder='* Станция метро']");
    private final By metroOption = By.xpath("//div[@class='select-search__select']//li[1]");
    private final By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By next = By.xpath("//button[text()='Далее']");

    public ForWhomPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void fillForm(String name, String surname, String address, String phone) {
        driver.findElement(this.name).sendKeys(name);
        driver.findElement(this.surname).sendKeys(surname);
        driver.findElement(this.address).sendKeys(address);
        driver.findElement(this.metro).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroOption)).click();
        driver.findElement(this.phone).sendKeys(phone);
    }

    public void clickNext() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(next));
        btn.click();
    }
}