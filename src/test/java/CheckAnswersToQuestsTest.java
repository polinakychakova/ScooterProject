import org.junit.Before;
import org.junit.Test;
import pageObject.MainPageSamokat;
import utils.ConfigBrowser;
import java.io.IOException;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class CheckAnswersToQuestsTest {

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
