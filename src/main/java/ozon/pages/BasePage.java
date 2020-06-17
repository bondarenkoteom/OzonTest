package ozon.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ozon.annotation.FieldName;
import ozon.utils.DriverManager;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class BasePage {
    public WebElement element;

    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public WebElement waitElementClickable(WebElement element) {
        return DriverManager.wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement visibilityElementWait(String nameField) {
        element = getFieldName(nameField);
        return DriverManager.wait.until(ExpectedConditions.visibilityOf(element));
    }

    public Boolean visibilityTextWait(WebElement element, String text) {
        return DriverManager.wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public WebElement findElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    /**
     * Получение веб элемента по его навзанию с помощбю reflection
     *
     * @param name      - имя поля
     * @param className - имя класс
     * @return - вернет веб элемент
     */
    public WebElement getField(String name, String className) {
        Class example = null;
        try {
            example = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Field> fields = Arrays.asList(example.getFields());
        for (Field field : fields) {
            if (field.getAnnotation(FieldName.class).name().equals(name)) {
                return DriverManager.getDriver().findElement(By.xpath(field.getAnnotation(FindBy.class).xpath()));
            }
        }
        Assert.fail("Не объявлен элемент с наименованием " + name);
        return null;
    }


    public void inputInField(String nameField, String value) {
        element = getFieldName(nameField);
        element.clear();
        element.sendKeys(value);
    }

    public void clearField(String nameField) {
        WebElement element = visibilityElementWait(nameField);
        element.click();
        waitElementClickable(element).sendKeys(Keys.CONTROL + "a");
        waitElementClickable(element).sendKeys(Keys.DELETE);
    }

    public void clickField(String nameField) {
        element = getFieldName(nameField);
        element.click();
    }

    public void clickCheckBox(String nameField) {
        element = getFieldName(nameField);
        element.click();
    }

    public void waitChange() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public abstract WebElement getFieldName(String name);


}