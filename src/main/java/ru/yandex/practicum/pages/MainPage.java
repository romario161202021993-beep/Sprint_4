package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы главной страницы
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By orderTopButton = By.xpath(".//button[text()='Заказать' and parent::div[contains(@class, 'Header')]]");
    private final By orderBottomButton = By.xpath(".//button[text()='Заказать' and parent::div[contains(@class, 'Home_FinishButton')]]");
    private final By faqQuestions = By.cssSelector("[data-accordion-component='AccordionItemButton']");
    private final By faqAnswers = By.cssSelector("[data-accordion-component='AccordionItemPanel']");
    private final By orderStatusButton = By.xpath(".//button[text()='Статус заказа']");
    private final By orderNumberField = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private final By goButton = By.xpath(".//button[text()='Go!']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }

    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    public void acceptCookies() {
        try {
            WebElement cookieBtn = driver.findElement(cookieButton);
            if (cookieBtn.isDisplayed()) {
                cookieBtn.click();
            }
        } catch (Exception e) {
            // Куки уже приняты или кнопка не найдена
        }
    }

    public void clickOrderButton(boolean isTopButton) {
        WebElement orderBtn;
        if (isTopButton) {
            orderBtn = wait.until(ExpectedConditions.elementToBeClickable(orderTopButton));
        } else {
            orderBtn = wait.until(ExpectedConditions.elementToBeClickable(orderBottomButton));
            // Скроллим к нижней кнопке
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderBtn);
        }
        orderBtn.click();
    }

    public void clickFaqQuestion(int index) {
        List<WebElement> questions = driver.findElements(faqQuestions);
        if (index < questions.size()) {
            WebElement question = questions.get(index);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
            wait.until(ExpectedConditions.elementToBeClickable(question)).click();
        }
    }

    public String getFaqAnswerText(int index) {
        List<WebElement> answers = driver.findElements(faqAnswers);
        if (index < answers.size()) {
            wait.until(ExpectedConditions.visibilityOf(answers.get(index)));
            return answers.get(index).getText();
        }
        return "";
    }

    public String getFaqQuestionText(int index) {
        List<WebElement> questions = driver.findElements(faqQuestions);
        if (index < questions.size()) {
            return questions.get(index).getText();
        }
        return "";
    }

    public void checkOrderStatus(String orderNumber) {
        // Сначала кликаем на кнопку статуса заказа
        WebElement statusBtn = wait.until(ExpectedConditions.elementToBeClickable(orderStatusButton));
        statusBtn.click();

        // Затем вводим номер заказа
        WebElement orderInput = wait.until(ExpectedConditions.elementToBeClickable(orderNumberField));
        orderInput.sendKeys(orderNumber);

        // И нажимаем Go!
        WebElement goBtn = wait.until(ExpectedConditions.elementToBeClickable(goButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", goBtn);
        goBtn.click();
    }

    public boolean isNotFoundImageDisplayed() {
        try {
            WebElement notFoundImage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("img[alt='Not found']")));
            return notFoundImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}