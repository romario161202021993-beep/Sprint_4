package ru.yandex.practicum.test;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory extends ExternalResource {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() {
        String browser = System.getProperty("browser", "chrome");

        if ("firefox".equals(browser)) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Override
    protected void after() {
        if (driver != null) {
            driver.quit();
        }
    }
}