package ozon.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ozon.basketv.BasketV;
import ozon.utils.DriverManager;

public class Hooks {

    @Before
    public void startUp() {
        DriverManager.startUp();
    }


    @After
    public void tearDown() {
        DriverManager.quit();
        BasketV.getBasketV().clear();
    }
}
