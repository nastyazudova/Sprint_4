package praktikum.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;



public class OrderMainInfoPage {
    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By Name = By.xpath(".//div/div[2]/div[2]/div[1]/input");
    private final By Surname = By.xpath(".//div/div[2]/div[2]/div[2]/input");
    private final By Address = By.xpath(".//div/div[2]/div[2]/div[3]/input");
    private final By Metro = By.xpath(".//div/div[2]/div[2]/div[4]/div");
    private final By PhoneNumber = By.xpath(".//div/div[2]/div[2]/div[5]/input");
    private final By NextButton = By.xpath(".//div/div[2]/div[3]/button");
    private final String MetroIdPrefix = ".//div/div[2]/div[2]/div[4]/div/div[2]/ul/li";

    public OrderMainInfoPage(WebDriver driver) {
        this.driver = driver;
    }



    public OrderMainInfoPage acceptCookies() {
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


    public void SetName(String name) {
        driver.findElement(Name).sendKeys(name);
    }

    public void SetSurname(String surname) {
        driver.findElement(Surname).sendKeys(surname);
    }

    public void SetAddress(String address) {
        driver.findElement(Address).sendKeys(address);
    }

    public void clickOnMetro() {
        driver.findElement(Metro).click();
    }

    public void clickOnFoundMetro(String id) {
        driver.findElement(By.xpath(MetroIdPrefix + id)).click();
    }

    public void SetNumber(String number) {
        driver.findElement(PhoneNumber).sendKeys(number);
    }

    private void ClickNextButton() {
        driver.findElement(NextButton).click();
    }

    public void FullFillData (String name, String surname, String address,String id, String number) {
        SetName(name);
        SetSurname(surname);
        SetAddress(address);
        clickOnMetro();
        clickOnFoundMetro(id);
        SetNumber(number);
        ClickNextButton();
    }

}
