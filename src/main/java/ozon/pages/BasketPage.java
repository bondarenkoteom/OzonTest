package ozon.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ozon.annotation.FieldName;
import ozon.basketv.BasketV;
import ozon.basketv.Product;
import ozon.utils.DriverManager;

import java.util.ArrayList;
import java.util.Comparator;

public class BasketPage extends BasePage {
    @FindBy(xpath = "//span[contains(text(), 'Удалить выбранные')]")
    @FieldName(name = "Удалить выбранные")
    public WebElement deleteChoose;

    @FindBy(xpath = "//div[text()='Удалить']")
    @FieldName(name = "Подтвердить удаление")
    public WebElement deleteConfirm;

    public void checkQuantityInBasket(String quantity) {
        try {
            String quantityXpath = "//span[contains(text(), '%s товаров')]";
            DriverManager.getDriver().findElement(By.xpath(String.format(quantityXpath, quantity)));
        } catch (NoSuchElementException e) {
            Assert.fail("Количество товаров не соотвествует. Ожидаемое количество: " + quantity);
        }
    }

    public void checkProducts() {
        ArrayList<String> actualBasket = new ArrayList<>();
        findElements(By.xpath("//span[@style='color: rgb(0, 26, 52);']")).forEach(webElement -> {
            actualBasket.add(webElement.getText());
        });

        BasketV.getBasketV().forEach(product -> {
            Assert.assertTrue("В корзине отсутствует продукт: " + product.getName(), actualBasket.contains(product.getName()));
        });
    }

    public Product getMaxPrice() {
        ArrayList<Product> products = BasketV.getBasketV();
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() == o2.getPrice()) return 0;
                return o1.getPrice() < o2.getPrice() ? 1 : -1;
            }
        };
        products.sort(productComparator);
        return products.get(0);
    }

    public void checkEmpty() {
        try {
            DriverManager.getDriver().findElement(By.xpath("//h1[contains(text(), 'Корзина пуста')]"));
        } catch (NoSuchElementException e) {
            Assert.fail("Ожидаемое состояние корзины не соответсвует актуальному. Корзина не пуста");
        }
    }


    @Override
    public WebElement getFieldName(String name) {
        return getField(name, "ozon.pages.BasketPage");
    }
}
