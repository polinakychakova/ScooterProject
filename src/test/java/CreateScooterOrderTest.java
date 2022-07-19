import org.junit.Before;
import org.junit.Test;
import pageObject.MainPageSamokat;
import pageObject.OrderPage;
import utils.ConfigBrowser;

import java.io.IOException;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateScooterOrderTest{
    @Before
    public void startUp() throws IOException {
//      Метод setBrowserName может запускать тест в разных браузерах в зависимости от передаваемого значения browserName.
//      Для вызова браузера Chrome значения browserName = "chrome"
        ConfigBrowser configBrowser = new ConfigBrowser();
        configBrowser.setBrowserName("chrome");
    }

    String[][] testData = new String[][]{
            {"ТестИмя", "ТестФамилия", "адрес рабочий", "78945612323", "Комсомольская", "12.01.2023", "Коммент для доставки", "двое суток", "black"},
            {"Бенедикт", "Камбербетч", "221Б Бейкер стрит", "445566778899", "Лубянка", "06.01.2064", " ", "трое суток", "grey"}
    };
    @Test
    public void order_topButton() {
        // создай драйвер для браузера Chrome
        // перейди на страницу тестового стенда
        boolean  isConfirmThatOrderProcessIsSuccessful  = open(baseUrl, MainPageSamokat.class)
                // приянтие куки
                .clickCookieButton()
                // нажми на кнопку "заказать" в верхней части страницы
                .clickOrderTopButton()
                .fillFirstPage(testData[0]) // заполнение 1 страницы регистрации
                .fillSecondPage(testData[0]) // заполнение 2 страницы регистрации
                .confirmThatOrderPageLoadIsSuccessful();
        assertTrue("Ошибка. Процесс регистрации заказа не завершен", isConfirmThatOrderProcessIsSuccessful);

    }

    @Test
    public void order_downButton() {
        // перейди на страницу тестового стенда
        boolean  isConfirmThatOrderProcessIsSuccessful  = open(baseUrl, MainPageSamokat.class)
            // приянтие куки
           .clickCookieButton()
           // нажми на кнопку "заказать" в средней части страницы
           .clickOrderDownButton()
           .fillFirstPage(testData[1]) // заполнение 1 страницы регистрации
           .fillSecondPage(testData[1]) // заполнение 2 страницы регистрации
           .confirmThatOrderPageLoadIsSuccessful();
        assertTrue("Ошибка. Процесс регистрации заказа не завершен", isConfirmThatOrderProcessIsSuccessful);

    }

}
