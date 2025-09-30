package ru.yandex.practicum.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.MainPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FaqTest {

    private final int index;
    private final String expectedQuestion;
    private final String expectedAnswer;

    public FaqTest(int index, String expectedQuestion, String expectedAnswer) {
        this.index = index;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testFaq() {
        WebDriver driver = factory.getDriver();
        MainPage page = new MainPage(driver);
        page.open();
        page.acceptCookies();

        var questions = page.getFaqQuestions();
        var answers = page.getFaqAnswers();

        assertEquals("Количество вопросов и ответов не совпадает", questions.size(), answers.size());

        String actualQuestion = questions.get(index).getText();
        assertEquals(expectedQuestion, actualQuestion);

        questions.get(index).click();

        for (int i = 0; i < 10; i++) {
            if (answers.get(index).isDisplayed()) break;
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        }

        assertTrue("Ответ не открылся", answers.get(index).isDisplayed());
        String actualAnswer = answers.get(index).getText();
        assertEquals(expectedAnswer, actualAnswer);
    }
}