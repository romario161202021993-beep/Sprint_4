package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By cookieBtn = By.id("rcc-confirm-button");
    private final By orderTop = By.xpath("//div[contains(@class, 'Header')]//button[text()='Заказать']");
    private final By orderBottom = By.xpath("//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");
    private final By faqQuestions = By.xpath("//div[@class='Home_FAQ__3uVm4']//div[@class='accordion__button']");
    private final By faqAnswers = By.xpath("//div[@class='Home_FAQ__3uVm4']//div[@class='accordion__panel']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void acceptCookies() {
        try {
            WebElement btn = driver.findElement(cookieBtn);
            if (btn.isDisplayed()) {
                btn.click();
            }
        } catch (Exception ignored) {}
    }

    public void clickOrder(boolean useTop) {
        WebElement btn = wait.until(d -> d.findElement(useTop ? orderTop : orderBottom));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
        wait.until(d -> btn.isDisplayed() && btn.isEnabled());
        btn.click();
    }

    public List<WebElement> getFaqQuestions() {
        return driver.findElements(faqQuestions);
    }

    public List<WebElement> getFaqAnswers() {
        return driver.findElements(faqAnswers);
    }
}