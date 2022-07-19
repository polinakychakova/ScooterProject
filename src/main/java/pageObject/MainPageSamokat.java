package pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;

public class MainPageSamokat {

    // локатор кнопки принятия куки-файлов
    @FindBy(how = How.ID, using = "rcc-confirm-button")
    private SelenideElement buttonСookie;

    // локатор кнопки "Заказать" в верху страницы
    @FindBy(how = How.CLASS_NAME, using = "Button_Button__ra12g")
    private SelenideElement buttonOrderTop;

    // локатор кнопки "Заказать" в коцне страницы
    @FindBy(how = How.CLASS_NAME, using = "Home_FinishButton__1_cWm")
    private SelenideElement buttonOrderDown;

    // найти локаторы всех вопросов
    @FindBy(how = How.XPATH, using = ".//*[@class='accordion__heading']")
    private ElementsCollection buttonsQuestion;

    // Найти локаторы всех ответов
    @FindBy(how = How.XPATH, using = ".//*[@class='accordion__panel']")
    private ElementsCollection buttonsAnswer;


    // локатор кнопки сверху "статус заказа"
    @FindBy(how = How.CLASS_NAME, using = "Header_Link__1TAG7")
    private SelenideElement buttonStatus;


    // ***************************** Методы для работы с главной страницей ****************************** //

    // вернуть количество кнопок вопросов
    public int buttonsNum() {
        return buttonsQuestion.size();
    }

    // метод нажатия кнопки "Заказать" вверху страницы
    public OrderPage clickOrderTopButton() {
        buttonOrderTop.isDisplayed();
        buttonOrderTop.click();
        return page(OrderPage.class);
    }

    // метод нажатия на кнопку заказа в середине страницы
    public OrderPage clickOrderDownButton() {
        buttonOrderDown.click();
        return page(OrderPage.class);
    }

    // метод нажатия на пункт вопроса
    public MainPageSamokat clickQuestionButton(int num) {
        buttonsQuestion.get(num).click();
        return this;
    }

    // метод проверки соответствия выпадающего ответа и вопроса
    public MainPageSamokat checkAnswer(int num) {
        String ans = $(byXpath(".//*[@class=\'accordion__panel\' and not(@hidden)]")).innerText();
        // проверка-сравнение что появилось и что должно было
        // первый аргумент получает текст определенного номера плашки(нужный текст), второй - единственная видимая строка ответов
        assertEquals(buttonsAnswer.get(num).getText(), ans);
        return this;
    }

    // метод нажатия на кнопку о принятии все куки-файлов
    public MainPageSamokat clickCookieButton() {
        if (buttonСookie.isDisplayed()) // файлы куки не были приняты ранее
            buttonСookie.click();
        return this;
    }

}





