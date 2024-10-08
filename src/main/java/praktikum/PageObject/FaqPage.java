package praktikum.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class FaqPage {
    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
    private final String questionsIdPrefix = "accordion__heading-";
    private final String answerIdPrefix = "accordion__panel-";

    public FaqPage(WebDriver driver) {
        this.driver = driver;
    }

    public FaqPage open() {
        driver.get(EnvConfig.BASE_URL);

        return this;
    }

    public FaqPage acceptCookies() {
        waitForCookiesFloater();
        driver.findElement(cookieButton).click();
        waitForCookiesFloaterToDisappear();

        return this;
    }

    private void waitForCookiesFloater() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
    }

    private void waitForCookiesFloaterToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));
    }

    public FaqPage clickOnQuestion(String id) {
        driver.findElement(By.id(questionsIdPrefix + id)).click();

        return this;
    }

    public FaqPage waitForAnswer(String id) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(answerIdPrefix + id)));

        return this;
    }

    public FaqPage checkAnswerIsInvisible(String id) {
        assert !driver.findElement(By.id(answerIdPrefix + id)).isDisplayed();

        return this;
    }

    public FaqPage checkQuestionIsCorrect(String id, String QText) {
        assertEquals("Текст вопроса не тот", QText, driver.findElement(By.id(questionsIdPrefix + id)).getText());
        return this;
    }

    public FaqPage checkAnswerIsCorrect(String id, String AText) {
        assertEquals("Текст ответа не тот", AText, driver.findElement(By.id(answerIdPrefix + id)).getText());
        return this;
    }
}
