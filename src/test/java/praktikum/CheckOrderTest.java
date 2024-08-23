package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.PageObject.MainPage;
import praktikum.PageObject.StatusPage;


public class CheckOrderTest {

    @Rule
    public DriverRule factory = new DriverRule();


    private String INVALID_ORDER_ID = "123";

    @Test
    public void invalidOrder() throws Exception {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.open();

        mainPage.clickOnStatus();
        mainPage.enterOrderId(INVALID_ORDER_ID);

        StatusPage statusPage = mainPage.clickOnGo();
        statusPage.checkErrorMessage();
    }



}
