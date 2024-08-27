package praktikum.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    private final By goButton = By.cssSelector(".Header_Button__28dPO");
    private final By orderField = By.className("Input_Input__1iN_Z");
    private final By statusButton = By.className("Header_Link__1TAG7");
    private final By orderButtonUp = By.xpath(".//div/div[1]/div[1]/div[2]/button[1]");
    private final By orderButtonDown = By.xpath(".//div/div[1]/div[4]/div[2]/div[5]/button");
    private final By cookieButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage acceptCookies() {
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
    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    public StatusPage clickOnGo() {
        driver.findElement(goButton).click();

        return new StatusPage(driver);
    }

    public void enterOrderId(String orderNumber) {
        driver.findElement(orderField).sendKeys(orderNumber);
    }

    public void clickOnStatus() {
        driver.findElement(statusButton).click();
    }

    public void  clickOnOrderButtonUp() {
        driver.findElement(orderButtonUp).click();
    }

    public void clickOnOrderButtonDown() {
        driver.findElement(orderButtonDown).click();
    }

}
