package praktikum;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.PageObject.MainPage;
import praktikum.PageObject.OrderDetailsPage;
import praktikum.PageObject.OrderMainInfoPage;

@RunWith(Parameterized.class)
public class OrderLowerButtonTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String number;
    private final String id;

    @Rule
    public DriverRule factory = new DriverRule();

    public OrderLowerButtonTest(String name, String surname, String address, String id,  String number) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.id = id;
        this.number = number;
    }



    @Parameterized.Parameters
    public static Object[][] orderData() {
        return new Object[][]{
                {"Алена", "Золина", "Москва дом 353","[1]", "89991234569"},
                {"Никита", "Петров", "Большая красная дом 35", "[2]","+79876543211"}
        };
    }

    @Test
    public void orderLowerButton()  {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.acceptCookies();
        mainPage.clickOnOrderButtonDown();

        var orderMainInfoPage = new OrderMainInfoPage(driver);

        orderMainInfoPage.FullFillData(name, surname, address,id, number);

        var orderDetailsPage = new OrderDetailsPage(driver);

        orderDetailsPage.FullFillData();
        orderDetailsPage.checkOrderSuccess();

    }
}
