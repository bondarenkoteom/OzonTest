package ozon.steps;

import io.cucumber.java.en.When;
import ozon.pages.BasePage;
import ozon.pages.SearchPage;

import java.util.NoSuchElementException;


public class SearchPageSteps {

    BasePage basePage = new SearchPage();
    SearchPage searchPage = new SearchPage();

    @When("Ограничиваем поле {string} до {string}")
    public void choosePrice(String nameField, String value) {
        basePage.clearField(nameField);
        basePage.inputInField(nameField, value);
    }

    @When("Отмечаем чексбокс {string}")
    public void clickRat(String nameField) {
        basePage.waitChange();
        basePage.clickCheckBox(nameField);
        basePage.waitChange();
    }

    @When("Добавляем {string} товары в количестве {string} штук")
    public void buyEven(String even, String quantity) {
        boolean flag = false;
        if (even.equalsIgnoreCase("четные")) {
            flag = true;
        }
        searchPage.addEvenCountToBasket(flag, Integer.parseInt(quantity));
    }

    @When("Добавляем {string} товары")
    public void buyAllEven(String even) {
        boolean flag = false;
        if (even.equalsIgnoreCase("четные")) {
            flag = true;
        }
        searchPage.addAllEvenToBasket(flag);
    }

    @When("Переходим на страницу {string}")
    public void goToBasket(String nameField) {
        basePage.clickField(nameField);
    }

    @When("Выбираем поле бренды {string}")
    public void chooseBrend(String value) {
        try {
            basePage.visibilityElementWait("Смотреть все");
            basePage.clickField("Смотреть все");
            basePage.visibilityElementWait("Найти");
            basePage.clearField("Найти");
            basePage.inputInField("Найти", value);
            basePage.waitChange();
            basePage.clickField(value);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }


    }

}

