package pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OrderPage {
    // локатор для поля ввода имени
    @FindBy(xpath = ".//input[@placeholder='* Имя']")
    private SelenideElement formName;

    // локатор для ввода фамилии
    @FindBy(xpath = ".//input[@placeholder='* Фамилия']")
    private SelenideElement formSurname;

    // локатор для ввода адреса
    @FindBy(xpath = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private SelenideElement formAddress;

    // локатор для кнопки для списка станций метро
    @FindBy(how = How.CLASS_NAME,using = "select-search__value")
    private SelenideElement buttonStation;

    // локатор для поля ввода телефона
    @FindBy(xpath = ".//*[@placeholder='* Телефон: на него позвонит курьер']")
    private SelenideElement formPhoneNum;

    // локатор для кнопки "Далее"
    @FindBy(how = How.XPATH, using = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private SelenideElement buttonNext;

    // локатор для ввода даты
    @FindBy(how = How.XPATH,using = ".//*[@placeholder=\"* Когда привезти самокат\"]")
    private SelenideElement dateInput;

    // локатор для выбора выпадения списка срока аренды
    @FindBy(how = How.CLASS_NAME,using = "Dropdown-root")
    private SelenideElement buttonRentTerm;

    // локатор для поля "Комментарий для курьера"
    @FindBy(xpath = ".//*[@placeholder='Комментарий для курьера']")
    private SelenideElement formComment;

    // локатор для кнопки "Заказать" в конце 2 страницы регистрации заказа
//    @FindBy(xpath = "//*[contains(text(), 'Заказать')]")
    @FindBy(className = "Order_Buttons__1xGrp")
    private SelenideElement buttonEndOrder;

    // локатор для конпки "Да"
    @FindBy(xpath = "//*[contains(text(), 'Да')]")
    private SelenideElement buttonYes;

    // локатор на появляющуюся форму окончания регистрации заказа
    @FindBy(className = "Order_Modal__YZ-d3")
    private SelenideElement formConfirm;

    // локатор для кнопки "Назад"
    @FindBy(xpath = "//*[contains(text(), 'Назад)]")
    private SelenideElement buttonBack;

    // коллекция локаторов на пункты списка со сроками аренды
    @FindBy(how = How.XPATH, using = ".//*[@class=\'Dropdown-option\']")
    public ElementsCollection buttonsRentTerm;

    // список кнопок со станциями метро
    @FindBy(how = How.CLASS_NAME, using = "Order_Text__2broi")
    private ElementsCollection buttonsStations;


    //************************************* Методы взаимодействия со страницей заказа ****************************************//

    // метод выбора из выпадающего списка со сроками аренды
    private OrderPage ChooseRentTerm(String text){
        buttonRentTerm.click();
        buttonsRentTerm.findBy(exactText(text)).click();
        return this;
    }

    //метод выбора цвета самоката по переданному ID
    private OrderPage ChooseColor(String color){
        $(By.id(color)).click();
        return this;
    }

    // метод выбора станции метро
    private OrderPage ChooseStation(String station){
        buttonStation.click();
        buttonsStations.findBy(exactText(station)).click();
        return this;
    }

    // метод заполнения поля "* Имя", "* Фамилия", "Телефон", "Адрес"
    public OrderPage fillFirstPage(String[] data){
        formName.isDisplayed(); //проверка на прогрузку 1 страницы регистрации
        formName.setValue(data[0]); //Имя
        formSurname.setValue(data[1]); //Фамилия
        formAddress.setValue(data[2]); //Адрес
        formPhoneNum.setValue(data[3]); //Телефон
        ChooseStation(data[4]);//Станция метро
        buttonNext.click();//переход на 2 страницу
        formComment.isDisplayed(); // проверка на то, что форма комментария видна, т.е. вторая страница прогрузилась
        return this;
    }

    // метод заполнения 2 страницы Регистрации Заказа. Поля: дата начала аренды, цвета самоката, комментария и периода аренды
    public OrderPage fillSecondPage(String[] data){
        formComment.setValue(data[6]); //Комментрий
        ChooseRentTerm(data[7]); //срок аренды
        ChooseColor(data[8]); //цвет самоката
        dateInput.click();
        dateInput.setValue(data[5]); // дата начала аренды
        buttonEndOrder.click();
        assertTrue(buttonYes.isDisplayed(), "кнопка Да видна");
        return this;
    }


    // метод проверки видимости формы, которая появляется при финальном подверждении заказа
    // кнопка не кликабельна, недостаток функциональности сайта
    public Boolean confirmThatOrderPageLoadIsSuccessful(){
        return  formConfirm.isDisplayed(); //дошли до подтверждения заказа
    }
}
