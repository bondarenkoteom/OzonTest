package ozon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ozon.annotation.FieldName;
import ozon.basketv.BasketV;
import ozon.basketv.Product;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//input[@qa-id='range-to'][1]")
    @FieldName(name = "Ограничние цены")
    public WebElement rangeTo;

    @FindBy(xpath = "//div[@value='Высокий рейтинг']//div[@class='ui-at4']")
    @FieldName(name = "Высокий рейтинг")
    public WebElement chooseRat;

    @FindBy(xpath = "//span[contains(text(), 'NFC')][1]")
    @FieldName(name = "NFC")
    public WebElement clickNFC;

    @FindBy(xpath = "//div[contains(@style, 'grid-column-start')]//div[text() = 'В корзину']")
    @FieldName(name = "Добавляем в корзину")
    public List<WebElement> addBasket;

    @FindBy(xpath = "//a[@data-widget='cart']")
    @FieldName(name = "Корзина")
    public WebElement basket;

    @FindBy(xpath = "//span[text()='Samsung']")
    @FieldName(name = "Samsung")
    public WebElement samsung;

    @FindBy(xpath = "//span[text()='Beats']")
    @FieldName(name = "Beats")
    public WebElement beats;

    @FindBy(xpath = "(//span[text()='Xiaomi'])[2]")
    @FieldName(name = "Xiaomi")
    public WebElement xiaomi;

    @FindBy(xpath = "//span[contains(text(), 'Посмотреть все')][1]")
    @FieldName(name = "Смотреть все")
    public WebElement checkAll;

    @FindBy(xpath = "//input[@class='ui-av9 ui-av4'][1]")
    @FieldName(name = "Найти")
    public WebElement searchBrend;

    private String productCard = "//div[@style='grid-column-start: span 12;']";

    public void addEvenCountToBasket(Boolean even, int quantity) {
        List<WebElement> list = findElements(By.xpath(productCard));
        if (even) {
            addSomeProductsToBasket(list, 1, quantity);
        } else {
            addSomeProductsToBasket(list, 0, quantity);
        }
    }

    public void addAllEvenToBasket(Boolean even) {
        List<WebElement> list = findElements(By.xpath(productCard));
        if (even) {
            addAllProductsToBasket(list, 1);
        } else {
            addAllProductsToBasket(list, 0);
        }
    }

    private void addAllProductsToBasket(List<WebElement> productList, int startIndex) {
        for (int i = startIndex; i < productList.size(); i += 2) {
            try {
                addProduct(productList, i);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }

    private void addSomeProductsToBasket(List<WebElement> productList, int start, int quantity) {
        for (int i = start; i < productList.size(); i += 2) {
            try {
                addProduct(productList, i);
                quantity--;
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
            if (quantity == 0) {
                break;
            }
        }
    }

    private void addProduct(List<WebElement> productList, int index) throws NoSuchElementException {
        productList.get(index).findElement(By.xpath(".//div[text()='В корзину']")).click();

        String productName = productList.get(index).findElement(By.xpath("(.//a)[2]")).getText();
        String productPrice = productList.get(index).findElement(By.xpath("(.//span[contains(text(), '\u20BD')])")).getText().replaceAll("[^0-9]", "");
        BasketV.getBasketV().add(new Product(productName, Long.parseLong(productPrice)));
        waitChange();

    }


    @Override
    public WebElement getFieldName(String name) {
        return getField(name, "ozon.pages.SearchPage");
    }
}
