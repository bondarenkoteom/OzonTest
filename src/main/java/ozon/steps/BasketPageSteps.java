package ozon.steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import ozon.basketv.BasketV;
import ozon.pages.BasketPage;


public class BasketPageSteps {
    BasketPage basketPage = new BasketPage();

    @When("Проверяем, что товары в корзине соотвествуют добавленным и их количетсво равно {string}")
    public void check(String quantity) {
        basketPage.checkQuantityInBasket(quantity);
        basketPage.checkProducts();
    }

    @When("Проверяем, что товары в корзине соотвествуют добавленным")
    public void check() {
        basketPage.checkProducts();
    }

    @When("Удаляем все товары и проверяем что корзина пуста")
    public void deleteAllProducts() {
        basketPage.clickField("Удалить выбранные");
        basketPage.clickField("Подтвердить удаление");
        basketPage.checkEmpty();
    }

    @Then("Прикладываем вложение с товарами добавленными в корзину")
    public void allProducts() {
        BasketV.getBasketV().forEach(product -> {
            Allure.addAttachment(product.getName(), product.toString());
        });
    }

    @Then("Вложение с наиболее дорогим товаром")
    public void maxPriceProduct() {
        Allure.addAttachment("Товар с максимальной ценой", basketPage.getMaxPrice().toString());

    }
}
