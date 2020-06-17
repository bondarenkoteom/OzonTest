package ozon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ozon.annotation.FieldName;

public class MainPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder= 'Искать на Ozon']")
    @FieldName(name = "Искать на Ozon")
    public WebElement searchBar;

    @FindBy(xpath = "//div[@class = 'ui-b ui-f8 ui-g9 ui-h4']")
    @FieldName(name = "Кнопка поиска")
    public WebElement clickSearchBar;


    @Override
    public WebElement getFieldName(String name) {
        return getField(name, "ozon.pages.MainPage");
    }
}
