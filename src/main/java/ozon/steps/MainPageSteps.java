package ozon.steps;

import io.cucumber.java.en.When;
import ozon.pages.BasePage;
import ozon.pages.MainPage;


public class MainPageSteps {


    BasePage basePage = new MainPage();


    @When("Выбрано поле {string}")
    public void selectSearchBar(String nameField) {
        basePage.visibilityElementWait(nameField).click();
    }

    @When("В поле {string} вводим нужный товар {string}")
    public void searchGoods(String nameField, String value) {
        basePage.inputInField(nameField, value);
    }

    @When("Нажимаем на кнопку {string}")
    public void clickButton(String nameFiled) {
        basePage.clickField(nameFiled);
    }


}
