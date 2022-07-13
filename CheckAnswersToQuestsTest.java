import org.junit.Before;
import org.junit.Test;
import pageObject.MainPageSamokat;
import utils.ConfigBrowser;
import java.io.IOException;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class CheckAnswersToQuestsTest {
//    final String[] answersArray = new String[]{
//            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
//            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
//            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
//            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
//            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
//            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
//            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
//            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
//    };


    @Before
    public void startUp() throws IOException {
//      Метод setBrowserName может запускать тест в разных браузерах в зависимости от передаваемого значения browserName.
//      Для вызова браузера Chrome значения browserName = "chrome"
        ConfigBrowser configBrowser = new ConfigBrowser();
        configBrowser.setBrowserName("chrome");
    }

    @Test
    public void test_QuesAns() {
        // создай драйвер для браузера Chrome
        // перейди на страницу тестового стенда
        MainPageSamokat mainPageSamokat  = open(baseUrl, MainPageSamokat.class)
                // приянтие куки
                .clickCookieButton();
        for(int i = 0; i < mainPageSamokat.buttonsNum(); i++) {
            mainPageSamokat
                    .clickQuestionButton(i)
                    // проверь, что выпал правильный текст
//                    .checkAnswer(answersArray[i], i);
                    .checkAnswer(i);
        }
    }
}
