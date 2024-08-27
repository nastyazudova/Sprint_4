package praktikum.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class OrderDetailsPage {
    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By DateField = By.xpath(".//div/div[2]/div[2]/div[1]/div/div/input");
    private final String DatePrefix = "react-datepicker__day--";
    private final By RentField = By.className("Dropdown-placeholder");
    private final By AmountOfDays = By.xpath(".//div/div[2]/div[2]/div[2]/div[2]/div[1]");
    private final By OrderButton = By.xpath(".//div/div[2]/div[3]/button[2]");
    private final By YesButton = By.xpath(".//div/div[2]/div[5]/div[2]/button[2]");
    private final By OrderSuccess = By.xpath(".//div/div/div[2]/div[5]/div[1]");

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderDetailsPage acceptCookies() {
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

    public void clickDateField() {
        driver.findElement(DateField).click();
    }

    LocalDate currentDate = LocalDate.now();
    String a = String.valueOf(currentDate);
    String b = String.valueOf(a.charAt(8));
    String c = String.valueOf(a.charAt(9));
    int d = (Integer.parseInt(b+c)+1);
    String e = ("0"+d);

    public void clickDate() {
        driver.findElement(By.className(DatePrefix+e)).click();
    }

    public void clickRentField() {
        driver.findElement(RentField).click();
    }

    public void clickAmountOfDays() {
        driver.findElement(AmountOfDays).click();
    }

    public void clickOrderButton() {
        driver.findElement(OrderButton).click();
    }

    public void clickYesButton() {
        driver.findElement(YesButton).click();
    }

    public void checkOrderSuccess() {
        assertThat(driver.findElement(OrderSuccess).getText(), containsString("Заказ оформлен"));
    }

    public void FullFillData() {
        clickDateField();
        clickDate();
        clickRentField();
        clickAmountOfDays();
        clickOrderButton();
        clickYesButton();
    }
}

